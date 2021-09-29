package com.example.infrastructure.framework

import com.example.application.service.ProjectHealthReportService
import com.example.domain.repository.BacklogProjectRepository
import com.example.infrastructure.externalapi.BacklogClient
import com.example.infrastructure.externalapi.BacklogProjectApi
import com.example.presentation.controller.HealthManagerController
import org.koin.dsl.module

val koinModules = module {
    single { HealthManagerController() }
    single { ProjectHealthReportService() }
    single { BacklogClient(getProperty("spaceId"), getProperty("apiKey")) }
    single<BacklogProjectRepository> { BacklogProjectApi(get()) }
}