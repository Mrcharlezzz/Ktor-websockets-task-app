package com.example

import io.ktor.server.application.*
import model.TaskRepo
import model.TaskRepository

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSockets()
    configureSerialization()
    configureRouting()
}
