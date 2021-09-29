package com.example.domain.model.healthmanage

import com.example.domain.model.issue.Issues
import com.example.domain.model.version.Version
import com.example.domain.value.healthmanage.HealthStatus
import com.example.domain.value.healthmanage.IdealValue
import com.example.domain.value.healthmanage.PlannedValue
import com.example.domain.value.issue.IssueCount

data class HealthReport(
    val version: Version,
    val issues: Issues,
    val plannedValue: PlannedValue,
    val idealValue: IdealValue,
    val plannedStatus: HealthStatus,
    val idealStatus: HealthStatus,
)
