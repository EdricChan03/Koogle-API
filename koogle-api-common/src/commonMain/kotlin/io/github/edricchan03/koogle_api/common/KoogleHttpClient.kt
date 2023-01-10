package io.github.edricchan03.koogle_api.common

import io.github.edricchan03.koogle_api.common.http.headers.SystemHeadersBuilder
import io.github.edricchan03.koogle_api.common.http.params.SystemParametersBuilder
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.http.*

/**
 * The base HTTP client for all Google API requests.
 * @param engine The client engine to use.
 * @param config Additional configuration to be passed to the [HttpClient].
 */
public abstract class KoogleHttpClient(
    engine: HttpClientEngine,
    config: HttpClientConfig<*>.() -> Unit
) {
    /**
     * The [HttpClient] to use in all HTTP requests.
     *
     * By default, the [system query parameters][systemParamsBuilder]
     * and [HTTP headers][systemHeadersBuilder] are applied to this client.
     */
    public val httpClient: HttpClient by lazy {
        HttpClient(engine) {
            config()
            defaultRequest {
                systemParamsBuilder.appendToBuilder(this)
                systemHeadersBuilder.appendToBuilder(this)
            }
        }
    }

    /** [System query parameters][SystemParametersBuilder] to be used. */
    private val systemParamsBuilder: SystemParametersBuilder = SystemParametersBuilder(ParametersBuilder())

    /** Sets the [system query parameters][SystemParametersBuilder] to be used. */
    public fun systemQueryParameters(config: SystemParametersBuilder.() -> Unit) {
        systemParamsBuilder.apply(config)
    }

    /** Retrieves the [system query parameters][SystemParametersBuilder]. */
    public val systemParameters: Parameters = systemParamsBuilder.build()

    /** [System HTTP headers][SystemHeadersBuilder] to be used. */
    private val systemHeadersBuilder: SystemHeadersBuilder = SystemHeadersBuilder(HeadersBuilder())

    /** Sets the [system HTTP headers][SystemHeadersBuilder] to be used. */
    public fun systemHeaders(config: SystemHeadersBuilder.() -> Unit) {
        systemHeadersBuilder.apply(config)
    }

    /** Retrieves the [system HTTP headers][SystemHeadersBuilder]. */
    public val systemHeaders: Headers = systemHeadersBuilder.build()
}
