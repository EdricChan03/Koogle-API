package io.github.edricchan03.koogle_api.common.http.headers

import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*

/**
 * Top-level class that all header builders should extend from.
 * @param headersBuilder The Ktor header builder to extend from.
 */
public abstract class KoogleHeadersBuilder(
    private val headersBuilder: HeadersBuilder
) : StringValuesBuilder by headersBuilder {
    /** Applies the headers to the specified [httpRequestBuilder]. */
    public fun appendToBuilder(httpRequestBuilder: HttpRequestBuilder) {
        httpRequestBuilder.headers.appendAll(headersBuilder)
    }

    /** Applies the headers to the specified [defaultRequestBuilder]. */
    public fun appendToBuilder(defaultRequestBuilder: DefaultRequest.DefaultRequestBuilder) {
        defaultRequestBuilder.headers.appendAll(headersBuilder)
    }

    // Needed as HeadersBuilder isn't a class that can be inherited from
    override fun build(): Headers {
        return headersBuilder.build()
    }
}
