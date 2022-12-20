package io.github.edricchan03.koogle_api.common.http.headers

import io.github.edricchan03.koogle_api.common.http.values.delegate.single.single
import io.github.edricchan03.koogle_api.common.http.values.floatValue
import io.github.edricchan03.koogle_api.common.http.values.stringValue
import io.ktor.client.request.*
import io.ktor.http.*

/**
 * HTTP headers that can be passed to any Google API.
 *
 * For more information, see the
 * [system parameters documentation](https://cloud.google.com/apis/docs/system-parameters).
 */
public class SystemHeaders(headersBuilder: HeadersBuilder) : Headers(headersBuilder) {
    /**
     * Authentication credentials.
     * See [Authentication overview](https://cloud.google.com/docs/authentication) for details.
     * ---
     * This variable corresponds to the `Authorization` HTTP header.
     */
    public var authorization: String? by stringValue(name = "Authorization").single()

    /**
     * HTTP Content-Type request header override.
     * ---
     * This variable corresponds to the `Content-Type` HTTP header.
     */
    public var contentType: ContentType? by contentTypeValue(name = "Content-Type").single()

    /**
     * [`FieldMask`](https://github.com/protocolbuffers/protobuf/blob/master/src/google/protobuf/field_mask.proto)
     * used for response filtering.
     * If empty, all fields should be returned unless documented otherwise.
     * ---
     * This variable corresponds to the `X-Goog-FieldMask` HTTP header.
     */
    public var fields: String? by stringValue(name = "X-Goog-FieldMask").single()

    /**
     * The intended HTTP method for the request.
     * Some network proxies don't accept all HTTP methods.
     * ---
     * This variable corresponds to the `X-HTTP-Method-Override` HTTP header.
     */
    public var httpMethodOverride: String? by stringValue(name = "X-HTTP-Method-Override").single()

    /**
     * Google API key. See https://cloud.google.com/docs/authentication/api-keys for details.
     * ---
     * This variable corresponds to the `X-Goog-Api-Key` HTTP header.
     */
    public var apiKey: String? by stringValue(name = "X-Goog-Api-Key").single()

    /**
     * A pseudo user identifier for charging per-user quotas.
     *
     * If not specified, the authenticated principal is used.
     *
     * If there is no authenticated principal, the client IP address will be used.
     *
     * When specified, a valid API key with service restrictions must be used to identify
     * the quota project. Otherwise, this parameter is ignored.
     * ---
     * This variable corresponds to the `X-Goog-Quota-User` HTTP header.
     */
    public var quotaUser: String? by stringValue(name = "X-Goog-Quota-User").single()

    /**
     * API client identification.
     *
     * The value is a space-separated list of `NAME "/" SEMVER` strings,
     * where the `NAME` should only contain lowercase letters, digits, and "-",
     * and the `SEMVER` should be a semantic version string.
     *
     * For example: `X-Goog-Api-Client: python/3.5.0 grpc-google-pubsub-v1/0.1.0-beta2 linux/2.7.0`.
     * ---
     * This variable corresponds to the `X-Goog-Api-Client` HTTP header.
     */
    public var apiClient: String? by stringValue(name = "X-Goog-Api-Client").single()

    /**
     * Contains a reason for making the request, which is intended to be recorded in
     * audit logging.
     * An example reason would be a support-case ticket number.
     * ---
     * This variable corresponds to the `X-Goog-Request-Reason` HTTP header.
     */
    public var requestReason: String? by stringValue(name = "X-Goog-Request-Reason").single()

    /**
     * A caller-specified project for quota and billing purposes.
     * The caller must have `serviceusage.services.use` permission on the project.
     * ---
     * This variable corresponds to the `X-Goog-User-Project` HTTP header.
     */
    public var userProject: String? by stringValue(name = "X-Goog-User-Project").single()

    /**
     * Timeout (in seconds, float value) for the server to finish processing the request.
     * This system param only applies to REST APIs for which client-side timeout is not applicable.
     * ---
     * This variable corresponds to the `X-Server-Timeout` HTTP header.
     */
    public var serverTimeout: Float? by floatValue(name = "X-Server-Timeout").single()

    /**
     * Passing additional parameters for gRPC requests in URL query format.
     * For example: `x-goog-request-params: service=pubsub.googleapis.com&release=2021-11-01r0`.
     * ---
     * This variable corresponds to the `x-goog-request-params` HTTP header.
     */
    public var requestParams: String? by stringValue(name = "x-goog-request-params").single()
}

/** Sets the [SystemHeaders] on the [HttpRequestBuilder] receiver. */
public fun HttpRequestBuilder.systemHeaders(headersInit: SystemHeaders.() -> Unit) {
    SystemHeaders(HeadersBuilder()).apply(headersInit).appendToBuilder(this)
}
