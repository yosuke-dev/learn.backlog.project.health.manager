package com.example.domain.model.issue

import com.example.domain.model.milestone.Milestones
import com.example.domain.value.shared.DueDate
import com.example.domain.value.issue.IssueId
import com.example.domain.value.issue.IssueStatus
import com.example.domain.value.project.ProjectId

data class Issue(
    val id: IssueId,
    val projectId: ProjectId,
    val milestones: Milestones,
    val issueStatus: IssueStatus,
    val dueDate: DueDate?
)
