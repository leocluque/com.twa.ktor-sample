package com.twa

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.twa.plugins.*
import io.ktor.network.tls.certificates.*
import org.slf4j.LoggerFactory
import java.io.File

fun main() {
    val keyStoreFile = File("build/keystorektor.jks")
    val keystore = generateCertificate(
        file = keyStoreFile,
        keyAlias = "MyKeyKtor",
        keyPassword = "123456",
        jksPassword = "123456"
    )
//    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
//        .start(wait = true)

    val environment = applicationEngineEnvironment {
        log = LoggerFactory.getLogger("ktor.application")
        connector {
            port = 8080
        }
        sslConnector(
            keyStore = keystore,
            keyAlias = "MyKeyKtor",
            keyStorePassword = { "123456".toCharArray() },
            privateKeyPassword = { "123456".toCharArray() }) {
            port = 8443
            keyStorePath = keyStoreFile
        }
        module(Application::module)
    }

    embeddedServer(Netty, environment).start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureTemplating()
    configureMonitoring()
    configureHTTP()
    configureRouting()
}
