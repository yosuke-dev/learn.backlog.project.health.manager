package com.example.domain.model

import com.example.domain.value.DueDate
import com.example.domain.value.IssueId
import com.example.domain.value.IssueStatus
import com.example.domain.value.ProjectId

data class Issue(
    val id: IssueId,
    val projectId: ProjectId,
    val issueStatus: IssueStatus,
    val dueDate: DueDate?
)
