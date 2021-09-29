package com.example.infrastructure.framework

import com.example.presentation.controller.HealthManagerController
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*
import org.koin.ktor.ext.inject

internal fun Routing.root() {
    route("/HealthManager"){
        val healthManagerController: HealthManagerController by inject()
        get("/{id}") {
            call.respond(healthManagerController.get(call.parameters.getOrFail<Long>("id")))
        }
    }
}