package io.github.edricchan03.koogle_api.common

import io.github.edricchan03.koogle_api.common.http.headers.SystemHeaders
import io.github.edricchan03.koogle_api.common.http.params.SystemParameters
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
     * By default, the system query parameters and HTTP headers are applied to this client.
     */
    public val httpClient: HttpClient by lazy {
        HttpClient(engine) {
            config()
            defaultRequest {
                systemQueryParameters.appendToBuilder(this)
                systemHeaders.appendToBuilder(this)
            }
        }
    }

    /** System query parameters to be used. */
    public var systemQueryParameters: SystemParameters = SystemParameters(ParametersBuilder())

    /** Sets the system query parameters to be used. */
    public fun systemQueryParameters(config: SystemParameters.() -> Unit) {
        systemQueryParameters.apply(config)
    }

    /** System HTTP headers to be used. */
    public var systemHeaders: SystemHeaders = SystemHeaders(HeadersBuilder())

    /** Sets the system HTTP headers to be used. */
    public fun systemHttpHeaders(config: SystemHeaders.() -> Unit) {
        systemHeaders.apply(config)
    }
}
