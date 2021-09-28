package com.example.domain.model

import com.example.domain.model.issue.Issues
import com.example.domain.model.version.Version
import com.example.domain.value.healthmanage.HealthStatus
import com.example.domain.value.issue.IssueCount

data class HealthReport(
    val version: Version,
    val issues: Issues,
    val plannedCount: IssueCount,
    val idealCount: IssueCount,
    val plannedStatus: HealthStatus,
    val idealStatus: HealthStatus,
)
