package com.example.presentation.response

data class HealthReportResponse(
    val version: Version,
    val issueCount: IssueCount,
    val expectedValue: ExpectedValue,
    val healthStatus: HealthStatus,
) {
    data class Version(
        val id: Long,
        val name: String,
    )
    data class ExpectedValue(
        val planned: Int,
        val ideal: Int,
    )
    data class IssueCount(
        val all: Int,
        val open: Int,
        val inProgress: Int,
        val resolve: Int,
        val closed: Int,
    )
    data class HealthStatus(
        val planned: HealthStatusParameter,
        val ideal: HealthStatusParameter,
    )
    data class HealthStatusParameter(
        val value: Float,
        val type: String,
    )
}
