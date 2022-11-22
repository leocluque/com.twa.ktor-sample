package com.twa

import io.ktor.server.application.*
import io.ktor.server.netty.*
import com.twa.plugins.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureSerialization()
    configureTemplating()
    configureMonitoring()
    configureHTTP()
    configureRouting()
}
