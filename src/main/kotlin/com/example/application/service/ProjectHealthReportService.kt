package com.example.application.service

import com.example.domain.model.healthmanage.HealthReport
import com.example.domain.model.issue.Issues
import com.example.domain.repository.BacklogProjectRepository
import com.example.domain.service.healthmanage.HealthMeasure
import com.example.domain.value.issue.IssueStatus
import com.example.domain.value.project.ProjectId
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class ProjectHealthReportService: KoinComponent {
    private val repository: BacklogProjectRepository by inject()

    fun getHealthReportByProjectId(projectId: Long): List<HealthReport> {
        val backlogProjectId = ProjectId(projectId)
        val versions = repository.getVersionsByProjectId(backlogProjectId)
        val issues = repository.getIssuesByVersions(backlogProjectId, versions.value.map { it.versionId })

        val today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())
        val filteredVersions = versions.value.filter { version ->
            val versionIssues = issues.value.filter { issue -> issue.milestones.value.map { milestone -> milestone.milestoneId.value }.contains(version.versionId.value) }
            !version.archived.value && versionIssues.size != versionIssues.count { issue -> issue.issueStatus === IssueStatus(IssueStatus.StatusType.Closed) }
        }

        return filteredVersions.map { version ->
            val versionIssues = Issues(issues.value.filter { issue -> issue.milestones.value.map { milestone -> milestone.milestoneId.value }.contains(version.versionId.value) })
            val plannedValue = HealthMeasure.measurePlannedValue(version, versionIssues, today)
            val idealValue = HealthMeasure.measureIdealValue(version, versionIssues, today)
            HealthReport(
                version,
                versionIssues,
                plannedValue,
                idealValue,
                HealthMeasure.measurePlannedHealth(versionIssues.getResolvedCount(), versionIssues.getClosedCount(), plannedValue),
                HealthMeasure.measureIdealHealth(versionIssues.getResolvedCount(), versionIssues.getClosedCount(), idealValue),
            )
        }
    }

}
