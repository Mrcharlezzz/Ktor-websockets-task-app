package com.example

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Configures plain HTTP routing.
 *
 * Endpoints:
 * - GET `/` → returns "Hello World!"
 * - Static resources under `/static` → serves files from `resources/static`.
 */
fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Healthy")
        }
        // Static plugin. Try Access `/static/wsClient.html`
        staticResources("/static", "static")
    }
}
