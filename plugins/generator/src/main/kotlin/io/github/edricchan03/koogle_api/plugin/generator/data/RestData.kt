package io.github.edricchan03.koogle_api.plugin.generator.data

import io.github.edricchan03.koogle_api.plugin.generator.data.json.GoogleJsonSchema
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// HTTP API-related data classes

@Serializable
public enum class HttpMethod {
    @SerialName("GET")
    Get,

    @SerialName("PATCH")
    Patch,

    @SerialName("PUT")
    Put
}


@Serializable
// See https://datatracker.ietf.org/doc/html/draft-zyp-json-schema-03#section-5.1
public enum class SchemaType {
    @SerialName("string")
    String,

    @SerialName("number")
    Number,

    @SerialName("integer")
    Integer,

    @SerialName("boolean")
    Boolean,

    @SerialName("object")
    Object,

    @SerialName("array")
    Array,

    @SerialName("null")
    Null,

    @SerialName("any")
    Any
}

public typealias RestResources = Map<String, RestResource>

/** Data class to represent metadata for an individual schema resource. */
@Serializable
public data class RestResource(
    /** Methods on this resource. */
    val methods: RestMethods? = null,
    val resources: RestResources? = null
)

public typealias RestItems = Map<String, GoogleJsonSchema>

public typealias RestParameters = Map<String, GoogleJsonSchema>

public typealias RestMethods = Map<String, RestMethod>

/** Data class to represent an individual method for [RestDescription.methods]. */
@Serializable
public data class RestMethod(
    /**
     * A unique ID for this method.
     * This property can be used to match methods between different versions of Discovery.
     */
    val id: String? = null,
    /**
     * The URI path of this REST method.
     * Should be used in conjunction with the servicePath property at the API-level.
     */
    val path: String? = null,
    /**
     * The URI path of this REST method in (RFC 6570) format without level 2
     * features ({+var}). Supplementary to the `path` property.
     */
    val flatPath: String? = null,
    /** HTTP method used by this method. */
    val httpMethod: HttpMethod? = null,
    /** Description of this method. */
    val description: String? = null,
    /** Whether this method requires an ETag to be specified. The ETag is sent as an HTTP If-Match or If-None-Match header. */
    val etagRequired: Boolean? = null,
    /** Details for all parameters in this method. */
    val parameters: RestParameters? = emptyMap(),
    /**
     * Ordered list of required parameters.
     * This serves as a hint to clients on how to structure their method signatures.
     * The array is ordered such that the most significant parameter appears first.
     */
    val parameterOrder: List<String>? = emptyList(),
    /** The schema for the request. */
    val request: Request? = null,
    /** The schema for the response. */
    val response: Response? = null,
    /** OAuth 2.0 scopes applicable to this method. */
    val scopes: List<String>? = emptyList(),
    /** Whether this method supports media downloads. */
    val supportsMediaDownload: Boolean? = null,
    /**
     * Indicates that downloads from this method should use the download service URL (i.e. "/download").
     * Only applies if the method supports media download.
     */
    val useMediaDownloadService: Boolean? = null,
    /** Whether this method supports media uploads. */
    val supportsMediaUpload: Boolean? = null,
    /** Media upload parameters. */
    val mediaUpload: MediaUpload? = null,
    /** Whether this method supports subscriptions. */
    val supportsSubscription: Boolean? = null
) {
    /** Data class to represent request parameters for [RestMethod.request]. */
    @Serializable
    public data class Request(
        /** Schema ID for the request schema. */
        @SerialName("\$ref")
        val metaRef: String? = null,
        /** The parameter name. */
        val parameterName: String? = null
    )

    /** Data class to represent response parameters for [RestMethod.response]. */
    @Serializable
    public data class Response(
        /** Schema ID for the response schema. */
        @SerialName("\$ref") val metaRef: String? = null
    )

    /** Data class to represent media upload parameters for [RestMethod.mediaUpload]. */
    @Serializable
    public data class MediaUpload(
        /** MIME Media Ranges for acceptable media uploads to this method. */
        val accept: List<String>? = emptyList(),
        /** Maximum size of a media upload, such as "1MB", "2GB" or "3TB". */
        val maxSize: String? = null,
        /** Supported upload protocols. */
        val protocols: Protocols? = null
    ) {
        /** Data class to represent media upload protocols for [MediaUpload.protocols]. */
        @Serializable
        public data class Protocols(
            /** Supports uploading as a single HTTP request. */
            val simple: Protocol? = null,
            /** Supports the Resumable Media Upload protocol. */
            val resumable: Protocol? = null
        ) {
            /** Data class to represent an individual media upload protocol for [Protocols]. */
            @Serializable
            public data class Protocol(
                /** True if this endpoint supports upload multipart media. */
                val multipart: Boolean? = true,
                /**
                 * The URI path to be used for upload.
                 * Should be used in conjunction with the [rootUrl][RestDescription.rootUrl] property at the API-level.
                 */
                val path: String? = null
            )
        }
    }
}
