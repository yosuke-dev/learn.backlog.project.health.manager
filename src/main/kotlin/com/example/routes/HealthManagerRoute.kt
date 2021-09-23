package com.example.routes

import com.nulabinc.backlog4j.BacklogClientFactory
import com.nulabinc.backlog4j.Issue
import com.nulabinc.backlog4j.api.option.GetIssuesCountParams
import com.nulabinc.backlog4j.api.option.GetIssuesParams
import com.nulabinc.backlog4j.conf.BacklogComConfigure
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.math.ceil

internal fun Route.healthManagerRoute(env: ApplicationEnvironment) {
    route("/") {
        get {
            // TODO: file structuring
            val spaceId = env.config.property("backlog.spaceId").getString()
            val apiKey = env.config.property("backlog.apiKey").getString()
            val projectId = env.config.property("backlog.projectId").getString()

            val configure = BacklogComConfigure(spaceId).apiKey(apiKey)
            val backlog = BacklogClientFactory(configure).newClient()

            val versions = backlog.getVersions(projectId).filter { version -> !version.archived }
            val versionIds = versions.filter { !it.archived }.sortedBy { it.startDate }.map { version -> version.id }

            val getIssueCountParams = GetIssuesCountParams(listOf(projectId))
            getIssueCountParams.milestoneIds(versionIds)
            val issueCount = backlog.getIssuesCount(getIssueCountParams)

            val stepCount = 100
            val getIssueParams = GetIssuesParams(listOf(projectId))
            getIssueParams.milestoneIds(versionIds)
            getIssueParams.count(stepCount)

            val issues = mutableListOf<Issue>()
            for (i in 0..issueCount step stepCount){
                getIssueParams.offset(i.toLong())
                issues.addAll(backlog.getIssues(getIssueParams))
            }

            val today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())
            val filteredVersions = versions.filter { version ->
                val versionIssues = issues.filter { issue -> issue.milestone.map { milestone -> milestone.id }.contains(version.id) }
                !version.archived && versionIssues.size != versionIssues.count { issue -> issue.status.statusType === Issue.StatusType.Closed}
            }
            val response = filteredVersions.map { version ->
                val versionIssues = issues.filter { issue -> issue.milestone.map { milestone -> milestone.id }.contains(version.id) }
                val versionPeriod = ChronoUnit.DAYS.between(version.startDate.toInstant(), version.releaseDueDate.toInstant()) + 1
                val currentPeriod = ChronoUnit.DAYS.between(version.startDate.toInstant(), today.toInstant()) + 1
                val adjustedCurrentPeriod = if (currentPeriod < 0) 0 else if (currentPeriod > versionPeriod) versionPeriod else currentPeriod
                val dayPoint = versionIssues.size.div(versionPeriod.toFloat())
                val idealPoint = dayPoint.times(adjustedCurrentPeriod)
                val openCount = versionIssues.count { issue -> issue.status.statusType === Issue.StatusType.Open }
                val inProgressCount = versionIssues.count { issue -> issue.status.statusType === Issue.StatusType.InProgress }
                val resolveCount = versionIssues.count { issue -> issue.status.statusType === Issue.StatusType.Resolved }
                val closedCount = versionIssues.count { issue -> issue.status.statusType === Issue.StatusType.Closed }
                val plannedCount = versionIssues.count { issue -> (issue.dueDate ?: version.releaseDueDate) < today }
                val idealCount = ceil(idealPoint).toInt()
                val currentParPlanned = ceil(resolveCount.plus(closedCount).times(10000F).div(plannedCount)).div(100F)
                val currentParIdeal = ceil(resolveCount.plus(closedCount).times(10000F).div(idealCount)).div(100F)
                val plannedStatus = when {
                    currentParPlanned < 80 ->  "Have a medical examination"
                    currentParPlanned < 95 ->  "Observation needed"
                    currentParPlanned < 105 ->  "Normal "
                    currentParPlanned < 120 ->  "Healthy"
                    else ->  "Very healthy"
                }
                val idealStatus = when {
                    currentParIdeal < 80 ->  "Have a medical examination"
                    currentParIdeal < 95 ->  "Observation needed"
                    currentParIdeal < 105 ->  "Normal "
                    currentParIdeal < 120 ->  "Healthy"
                    else ->  "Very healthy"
                }
                ResponseData(
                    version.id,
                    version.name,
                    versionIssues.size,
                    openCount,
                    inProgressCount,
                    resolveCount,
                    closedCount,
                    plannedCount,
                    idealCount,
                    currentParPlanned,
                    currentParIdeal,
                    plannedStatus,
                    idealStatus,
                )
            }
            call.respond(HttpStatusCode.OK, response)
        }
    }
}

data class ResponseData(
    val versionId: Long,
    val versionName: String,
    val issueCount: Int,
    val openCount: Int,
    val inProgressCount: Int,
    val resolveCount: Int,
    val closedCount: Int,
    val plannedCount: Int,
    val idealCount: Int,
    val currentParPlanned: Float,
    val currentParIdeal: Float,
    val plannedStatus: String,
    val idealStatus: String,
)