package com.example.domain.model.issue

import com.example.domain.value.DueDate
import com.example.domain.value.issue.IssueId
import com.example.domain.value.issue.IssueStatus
import com.example.domain.value.project.ProjectId

data class Issue(
    val id: IssueId,
    val projectId: ProjectId,
    val issueStatus: IssueStatus,
    val dueDate: DueDate?
)
