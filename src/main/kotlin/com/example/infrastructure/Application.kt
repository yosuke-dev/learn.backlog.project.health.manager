package com.example.infrastructure

import com.example.infrastructure.framework.koinModules
import com.example.infrastructure.framework.root
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.routing.*
import org.koin.ktor.ext.Koin

fun main(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args)).start(true)
}

fun Application.module() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
    install(Koin) {
        modules(koinModules)
        properties( mapOf(
            Pair("spaceId",environment.config.property("backlog.spaceId").getString()),
            Pair("apiKey",environment.config.property("backlog.apiKey").getString()),
        ))
    }
    routing {
        root()
    }
}