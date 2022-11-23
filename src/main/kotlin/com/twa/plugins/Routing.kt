package com.twa.plugins

import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import java.io.File

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondFile(File("./src/main/resources/static/index.html"))
        }

        get("/validateHeader") {
           val headerNameList = (call.request.headers as NettyApplicationRequestHeaders).entries()
            val headerName = mutableListOf<String>()
            headerNameList.toList().forEach {
                headerName.add(it.key)
            }

            application.environment.log.info(headerName.toString())

            if (call.request.headers["bearer-token"] == null) {
                call.respond(HttpStatusCode.Forbidden)
            }
            call.respondFile(File("./src/main/resources/static/index.html"))

        }

        get("/deeplink") {
            call.respondRedirect("https://www.twa-poc.com/challenge/bio")
        }

        static("/.well-known") {
            resources("static")
            default("assetlinks.json")
        }
    }
}