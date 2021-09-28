package com.example.domain.model.issue

import com.example.domain.value.issue.*

data class Issues(
    val value: List<Issue>
) {
    fun getAllCount(): AllCount {
        return AllCount(value.count())
    }
    fun getOpenCount(): OpenCount {
        return OpenCount(value.count { issue -> issue.issueStatus.value === IssueStatus.StatusType.Open })
    }
    fun getInProgressCount(): InProgressCount {
        return InProgressCount(value.count { issue -> issue.issueStatus.value === IssueStatus.StatusType.InProgress })
    }
    fun getResolvedCount(): ResolvedCount {
        return ResolvedCount(value.count { issue -> issue.issueStatus.value === IssueStatus.StatusType.Resolved })
    }
    fun getClosedCount(): ClosedCount {
        return ClosedCount(value.count { issue -> issue.issueStatus.value === IssueStatus.StatusType.Closed })
    }
    fun getCustomCount(): CustomCount {
        return CustomCount(value.count { issue -> issue.issueStatus.value === IssueStatus.StatusType.Custom })
    }
}
