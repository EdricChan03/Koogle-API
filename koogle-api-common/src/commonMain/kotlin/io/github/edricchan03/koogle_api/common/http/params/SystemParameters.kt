package io.github.edricchan03.koogle_api.common.http.params

import io.github.edricchan03.koogle_api.common.http.headers.contentTypeValue
import io.github.edricchan03.koogle_api.common.http.values.booleanValue
import io.github.edricchan03.koogle_api.common.http.values.enumValue
import io.github.edricchan03.koogle_api.common.http.values.stringValue
import io.ktor.http.*

/**
 * Query parameters that can be passed to any Google API.
 *
 * For more information, see the
 * [system parameters documentation](https://cloud.google.com/apis/docs/system-parameters).
 */
public class SystemParameters(
    parametersBuilder: ParametersBuilder
) : Parameters(parametersBuilder) {
    /** Alternative response format. Supported values are json (default), media, proto. */
    public var alt: Alt? by enumValue(toEnum = { Alt.getEnumByValue(it) })

    /** JSON error format. Supported values are 1, 2 (default). */
    public var xgafv: Xgafv? by enumValue(name = "\$.xgafv", toEnum = { Xgafv.getEnumByValue(it) })

    /** JSONP callback parameter. */
    public var callback: String? by stringValue()

    /** HTTP Content-Type request header override. */
    public var contentType: ContentType? by contentTypeValue(name = "\$ct")

    /**
     * `FieldMask` used for response filtering.
     * If empty, all fields should be returned unless documented otherwise.
     */
    public var fields: String? by stringValue()

    /** Google API key. See [API keys](https://cloud.google.com/docs/authentication/api-keys) for details. */
    public var key: String? by stringValue()

    /** Pretty-print JSON response. Supported values are `true` (default), `false`. */
    public var prettyPrint: Boolean? by booleanValue()

    /**
     * A pseudo user identifier for charging per-user quotas.
     *
     * If not specified, the authenticated principal is used.
     *
     * If there is no authenticated principal, the client IP address will be used.
     *
     * When specified, a valid API key with service restrictions must be used to identify
     * the quota project. Otherwise, this parameter is ignored.
     */
    public var quotaUser: String? by stringValue()

    /** Force to output proto default values for JSON responses. */
    public var outputDefaults: String? by stringValue()

    /** Unique query parameter to disable request caching. */
    public var unique: String? by stringValue()

    public enum class Alt(public val value: String) {
        /** Responses with Content-Type of application/json */
        Json("json"),

        /** Media download with context-dependent Content-Type */
        Media("media"),

        /** Responses with Content-Type of application/x-protobuf */
        Proto("proto");

        public companion object {
            /** Retrieves the enum by its parameter value. */
            public fun getEnumByValue(value: String): Alt? = Alt.values().find { it.value == value }
        }
    }

    public enum class Xgafv(public val value: String) {
        /** V1 error format */
        Version1("1"),

        /** V2 error format */
        Version2("2");

        public companion object {
            /** Retrieves the enum by its parameter value. */
            public fun getEnumByValue(value: String): Xgafv? = Xgafv.values().find { it.value == value }
        }
    }
}

/** Sets the [SystemParameters] on the [URLBuilder] receiver. */
public fun URLBuilder.systemParameters(paramsInit: SystemParameters.() -> Unit) {
    SystemParameters(parameters).apply(paramsInit).appendToBuilder(this)
}
