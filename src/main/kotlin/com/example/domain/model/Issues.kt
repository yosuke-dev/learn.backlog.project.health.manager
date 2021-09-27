package com.example.domain.model

import com.example.domain.value.IssueCount
import com.example.domain.value.IssueId
import com.example.domain.value.IssueStatus
import com.example.domain.value.ProjectId

data class Issues(
    val issues: List<Issue>
) {
    fun getOpenCount(): IssueCount {
        return IssueCount(issues.count { issue -> issue.issueStatus.value === IssueStatus.StatusType.Open })
    }
    fun getInProgressCount(): IssueCount {
        return IssueCount(issues.count { issue -> issue.issueStatus.value === IssueStatus.StatusType.InProgress })
    }
    fun getResolvedCount(): IssueCount {
        return IssueCount(issues.count { issue -> issue.issueStatus.value === IssueStatus.StatusType.Resolved })
    }
    fun getClosedCount(): IssueCount {
        return IssueCount(issues.count { issue -> issue.issueStatus.value === IssueStatus.StatusType.Closed })
    }
    fun getCustomCount(): IssueCount {
        return IssueCount(issues.count { issue -> issue.issueStatus.value === IssueStatus.StatusType.Custom })
    }
}
