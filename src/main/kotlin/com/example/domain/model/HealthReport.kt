package com.example.domain.model

import com.example.domain.value.*

data class HealthReport(
    val version: Version,
    val issues: Issues,
    val plannedCount: IssueCount,
    val idealCount: IssueCount,
    val currentParPlanned: ProgressRate,
    val currentParIdeal: ProgressRate,
    val plannedStatus: HealthStatus,
    val idealStatus: HealthStatus,
)
