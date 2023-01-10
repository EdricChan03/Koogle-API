package io.github.edricchan03.koogle_api.common.http.params

import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*

/**
 * Top-level class that all query parameter builders should extend from.
 * @param parametersBuilder The Ktor header builder to extend from.
 */
public abstract class KoogleParametersBuilder(private val parametersBuilder: ParametersBuilder) :
    ParametersBuilder by parametersBuilder {
    /** Applies the parameters to the specified [urlBuilder]. */
    public fun appendToBuilder(urlBuilder: URLBuilder) {
        urlBuilder.parameters.appendAll(parametersBuilder)
    }

    /** Applies the parameters to the specified [httpRequestBuilder]. */
    public fun appendToBuilder(httpRequestBuilder: HttpRequestBuilder) {
        appendToBuilder(httpRequestBuilder.url)
    }

    /** Applies the parameters to the specified [defaultRequestBuilder]. */
    public fun appendToBuilder(defaultRequestBuilder: DefaultRequest.DefaultRequestBuilder) {
        appendToBuilder(defaultRequestBuilder.url)
    }
}
