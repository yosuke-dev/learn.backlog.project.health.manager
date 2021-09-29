package com.example.infrastructure.externalapi

import com.example.domain.model.issue.Issues
import com.example.domain.model.milestone.Milestone
import com.example.domain.model.milestone.Milestones
import com.example.domain.model.version.Versions
import com.example.domain.repository.BacklogProjectRepository
import com.example.domain.value.shared.DueDate
import com.example.domain.value.shared.StartDate
import com.example.domain.value.issue.IssueId
import com.example.domain.value.issue.IssueStatus
import com.example.domain.value.milestone.MilestoneId
import com.example.domain.value.milestone.MilestoneName
import com.example.domain.value.project.ProjectId
import com.example.domain.value.version.Archived
import com.example.domain.value.version.VersionId
import com.example.domain.value.version.VersionName
import com.nulabinc.backlog4j.Issue
import com.nulabinc.backlog4j.api.option.GetIssuesCountParams
import com.nulabinc.backlog4j.api.option.GetIssuesParams

class BacklogProjectApi(private val backlogClient: BacklogClient): BacklogProjectRepository {
    override fun getVersionsByProjectId(projectId: ProjectId): Versions {
        val versions = backlogClient.client.getVersions(projectId.value)
        return Versions(versions.map { version ->
            com.example.domain.model.version.Version(
                VersionId(version.id),
                VersionName(version.name),
                StartDate(version.startDate),
                DueDate(version.releaseDueDate),
                Archived(version.archived)
            )
        })
    }

    override fun getIssuesByVersions(projectId: ProjectId, versionIds: List<VersionId>): Issues {
        val getIssueCountParams = GetIssuesCountParams(listOf(projectId.value))
        getIssueCountParams.milestoneIds(versionIds.map { it.value })
        val issueCount = backlogClient.client.getIssuesCount(getIssueCountParams)

        val stepCount = 100
        val getIssueParams = GetIssuesParams(listOf(projectId.value))
        getIssueParams.milestoneIds(versionIds.map { it.value })
        getIssueParams.count(stepCount)

        val issues = mutableListOf<Issue>()
        for (i in 0..issueCount step stepCount){
            getIssueParams.offset(i.toLong())
            issues.addAll(backlogClient.client.getIssues(getIssueParams))
        }

        return Issues(issues.map { issue ->
            com.example.domain.model.issue.Issue(
                IssueId(issue.id),
                ProjectId(issue.projectId),
                Milestones(issue.milestone.map { milestone -> Milestone(MilestoneId(milestone.id), MilestoneName(milestone.name)) }),
                IssueStatus(when(issue.status.statusType){
                    Issue.StatusType.Open -> IssueStatus.StatusType.Open
                    Issue.StatusType.InProgress -> IssueStatus.StatusType.InProgress
                    Issue.StatusType.Resolved -> IssueStatus.StatusType.Resolved
                    Issue.StatusType.Closed -> IssueStatus.StatusType.Closed
                    Issue.StatusType.Custom -> IssueStatus.StatusType.Custom
                    null -> IssueStatus.StatusType.Custom
                }),
                issue.dueDate?.let { DueDate(it) }
            )
        })
    }
}