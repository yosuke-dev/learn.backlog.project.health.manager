package com.example.presentation.controller

import com.example.application.service.ProjectHealthReportService
import com.example.presentation.response.HealthReportResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HealthManagerController: KoinComponent {
    private val service: ProjectHealthReportService by inject()

    fun get(id: Long): List<HealthReportResponse> {
        val result = service.getHealthReportByProjectId(id)
        if (result.isEmpty()) throw RuntimeException("no active milestones.")
        return result.map {
            HealthReportResponse(
                HealthReportResponse.Version(
                    it.version.versionId.value,
                    it.version.versionName.value
                ),
                HealthReportResponse.IssueCount(
                    it.issues.getAllCount().value,
                    it.issues.getOpenCount().value,
                    it.issues.getInProgressCount().value,
                    it.issues.getResolvedCount().value,
                    it.issues.getClosedCount().value
                ),
                HealthReportResponse.ExpectedValue(
                    it.plannedValue.value,
                    it.idealValue.value
                ),
                HealthReportResponse.HealthStatus(
                    HealthReportResponse.HealthStatusParameter(
                        it.plannedStatus.value,
                        it.plannedStatus.getType().name
                    ),
                    HealthReportResponse.HealthStatusParameter(
                        it.idealStatus.value,
                        it.idealStatus.getType().name
                    )
                )
            )
        }
    }
}