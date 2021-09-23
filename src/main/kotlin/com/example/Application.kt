package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.routes.*
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.routing.*

fun main(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args)).start(true)
}

fun Application.module() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
    routing {
        healthManagerRoute(environment)
    }
}