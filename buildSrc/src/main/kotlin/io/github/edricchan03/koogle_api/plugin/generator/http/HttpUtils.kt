package io.github.edricchan03.koogle_api.plugin.generator.http

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

val client get() = HttpClient(CIO) {
    install(HttpRequestRetry)
    install(ContentNegotiation) {
        json()
    }
    expectSuccess = true
}
