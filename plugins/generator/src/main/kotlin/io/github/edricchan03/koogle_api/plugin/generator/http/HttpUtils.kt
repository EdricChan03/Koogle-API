package io.github.edricchan03.koogle_api.plugin.generator.http

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

/**
 * Creates an [HttpClient] for performing HTTP calls.
 * Additional [configuration][config] can be passed to the HTTP client if necessary.
 * @param config Configuration to be passed to the HTTP client
 */
internal fun createClient(
    config: HttpClientConfig<CIOEngineConfig>.() -> Unit = {}
) = HttpClient(CIO) {
    install(HttpRequestRetry)
    install(ContentNegotiation) {
        json()
    }
    expectSuccess = true

    config()
}
