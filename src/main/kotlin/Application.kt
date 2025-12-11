package com.example

import com.example.model.SqliteTaskRepository
import io.ktor.server.application.*

/**
 * Application entry point.
 *
 * Delegates to Ktor's Netty engine.
 */
fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

/**
 * Primary Ktor module.
 *
 * Wires the application components:
 * - database (SQLite via Exposed)
 * - WebSockets (task streaming and realtime updates)
 * - JSON serialization (Kotlinx)
 * - HTTP routing
 */
fun Application.module() {

    val repository = SqliteTaskRepository()
    configureDatabases()
    configureSockets(repository)
    configureSerialization()
    configureRouting()
}
