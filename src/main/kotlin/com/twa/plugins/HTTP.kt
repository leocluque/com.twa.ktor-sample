package com.twa.plugins

import io.ktor.server.plugins.httpsredirect.*
import io.ktor.server.plugins.hsts.*
import io.ktor.http.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureHTTP() {
//    install(HttpsRedirect) {
//            // The port to redirect to. By default 443, the default HTTPS port.
//            sslPort = 443
//            // 301 Moved Permanently, or 302 Found redirect.
//            permanentRedirect = true
//        }
//    install(HSTS) {
//        includeSubDomains = true
//    }
    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.AccessControlAllowHeaders)
        allowHeadersPrefixed("custom-")
        exposeHeader("custom-header")
        allowHeader("bearer-token")
        exposeHeader("bearer-token")
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

}
