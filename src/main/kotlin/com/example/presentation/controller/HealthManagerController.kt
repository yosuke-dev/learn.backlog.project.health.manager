package com.example.presentation.controller

import com.example.application.service.ProjectHealthReportService
import com.example.domain.model.healthmanage.HealthReport
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HealthManagerController: KoinComponent {
    private val service: ProjectHealthReportService by inject()

//    data class BookInput(val bookId: Int)
//    data class BookPostInput(@field:Size(max = 20) val title: String,@field:Size(max = 20)  val author: String)
//    data class BookOutput(val id: Int, val title: String, val author: String)

    fun get(id: Long): List<HealthReport> {
        val result = service.getHealthReportByProjectId(id)
        if (result.isEmpty()) throw RuntimeException("no active milestones.")
        return result
    }
}