package com.twa.plugins

import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import java.io.File

fun Application.configureRouting() {

    routing {
        get("/") {
            if (call.request.headers["MyCustomHeader"] == null) {
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