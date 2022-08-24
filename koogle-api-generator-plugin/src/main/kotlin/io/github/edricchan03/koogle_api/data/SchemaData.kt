package io.github.edricchan03.koogle_api.data

import io.github.edricchan03.koogle_api.data.json.GoogleJsonSchema
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// HTTP API-related data classes

@Serializable
enum class HttpMethod {
    @SerialName("GET") Get,
    @SerialName("PATCH") Patch,
    @SerialName("PUT") Put
}


@Serializable
// See https://datatracker.ietf.org/doc/html/draft-zyp-json-schema-03#section-5.1
enum class SchemaType {
    @SerialName("string") String,
    @SerialName("number") Number,
    @SerialName("integer") Integer,
    @SerialName("boolean") Boolean,
    @SerialName("object") Object,
    @SerialName("array") Array,
    @SerialName("null") Null,
    @SerialName("any") Any
}

typealias SchemaResources = Map<String, SchemaResource>

/** Data class to represent metadata for an individual schema resource. */
@Serializable
data class SchemaResource(
    /** Methods on this resource. */
    val methods: SchemaMethods? = null,
    val resources: SchemaResources? = null
)

typealias SchemaItems = Map<String, SchemaItem>

/** Data class to represent metadata for an individual schema. */
@Serializable
data class SchemaItem(
    /** Unique identifier for this schema. Example: `URL` */
    val id: String? = null,
    /**
     * The value type for this schema.
     *
     * A list of values can be found at the
     * ["type" section in the JSON Schema](http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.1).
     */
    val type: SchemaType? = null,
    /** A reference to another schema. The value of this property is the ID of another schema. */
    @SerialName("\$ref") val metaRef: String? = null,
    /** A description of this object. */
    val description: String? = null,
    /** The default value of this property (if one exists). */
    val default: String? = null,
    /** Whether the parameter is required. */
    val required: Boolean? = null,
    /**
     * An additional regular expression or key that helps constrain the value.
     *
     * For more details see the
     * [Type and Format Summary](https://developers.google.com/discovery/v1/type-format).
     */
    val format: String? = null,
    /** The regular expression this parameter must conform to. */
    val pattern: String? = null,
    /** The minimum value of this parameter. */
    val minimum: String? = null,
    /** The maximum value of this parameter. */
    val maximum: String? = null,
    /** Values this parameter may take (if it is an enum). */
    val enum: List<String>? = emptyList(),
    /** The descriptions for the enums. Each position maps to the corresponding value in the enum array. */
    val enumDescriptions: List<String>? = emptyList(),
    /** Whether this parameter may appear multiple times. */
    val repeated: Boolean? = null,
    /** Whether this parameter goes in the query or the path for REST requests. */
    val location: String? = null,
    /** If this is a schema for an object, list the schema for each property of this object. */
    val properties: Map<String, GoogleJsonSchema>? = null,
    /**
     * If this is a schema for an object, this property is the schema for any
     * additional properties with dynamic keys on this object.
     */
    val additionalProperties: GoogleJsonSchema? = null,
    /** If this is a schema for an array, this property is the schema for each element in the array. */
    val items: GoogleJsonSchema? = null,
    /** Additional information about this property. */
    val annotations: SchemaAnnotations? = null
)

typealias SchemaParameters = Map<String, SchemaParameter>

/** Data class to represent a single parameter in [SchemaRes.parameters]. */
@Serializable
data class SchemaParameter(
    /** Unique identifier for this schema. */
    val id: String? = null,
    /**
     * The value type for this schema.
     *
     * A list of values can be found at the
     * ["type" section in the JSON Schema](http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.1).
     */
    val type: SchemaType? = null,
    /** A reference to another schema. The value of this property is the ID of another schema. */
    @SerialName("\$ref") val metaRef: String? = null,
    /** A description of this object. */
    val description: String? = null,
    /** The default value of this property (if one exists). */
    val default: String? = null,
    /** Whether the parameter is required. */
    val required: Boolean? = null,
    /**
     * An additional regular expression or key that helps constrain the value.
     *
     * For more details, see the
     * [Type and Format Summary](https://developers.google.com/discovery/v1/type-format).
     */
    val format: String? = null,
    /** The regular expression this parameter must conform to. */
    val pattern: String? = null,
    /** The minimum value of this parameter. */
    val minimum: String? = null,
    /** The maximum value of this parameter. */
    val maximum: String? = null,
    /** Values this parameter may take (if it is an enum). */
    val enum: List<String>? = emptyList(),
    /** The descriptions for the enums. Each position maps to the corresponding value in the enum array. */
    val enumDescriptions: List<String>? = emptyList(),
    /** Whether this parameter may appear multiple times. */
    val repeated: Boolean? = null,
    /** Whether this parameter goes in the query or the path for REST requests. */
    val location: String? = null,
    /** If this is a schema for an object, list the schema for each property of this object. */
    val properties: Map<String, GoogleJsonSchema>? = null,
    /** If this is a schema for an object, this property is the schema for any additional properties with dynamic keys on this object. */
    val additionalProperties: GoogleJsonSchema? = null,
    /** If this is a schema for an array, this property is the schema for each element in the array. */
    val items: GoogleJsonSchema? = null,
    /** Additional information about this property. */
    val annotations: SchemaAnnotations? = null
)

/** Data class to represent [SchemaParameter.annotations]. */
@Serializable
data class SchemaAnnotations(
    /** A list of methods that require this property on requests. */
    val required: List<String>? = emptyList()
)

typealias SchemaMethods = Map<String, SchemaMethod>

/** Data class to represent an individual method for [SchemaRes.methods]. */
@Serializable
data class SchemaMethod(
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
    /** HTTP method used by this method. */
    val httpMethod: HttpMethod? = null,
    /** Description of this method. */
    val description: String,
    /** Details for all parameters in this method. */
    val parameters: SchemaParameters? = null,
    /**
     * Ordered list of required parameters.
     * This serves as a hint to clients on how to structure their method signatures.
     * The array is ordered such that the most significant parameter appears first.
     */
    val parameterOrder: List<String>? = emptyList(),
    /** The schema for the request. */
    val request: PropertyRef? = null,
    /** The schema for the response. */
    val response: PropertyRef? = null,
    /** OAuth 2.0 scopes applicable to this method. */
    val scopes: List<String>? = emptyList(),
    /** Whether this method supports media downloads. */
    val supportsMediaDownload: Boolean? = null,
    /** Whether this method supports media uploads. */
    val supportsMediaUpload: Boolean? = null,
    /** Media upload parameters. */
    val mediaUpload: SchemaMethodMediaUpload? = null,
    /** Whether this method supports subscriptions. */
    val supportsSubscription: Boolean? = null
)

/** Generic data class that holds a property that can be used to reference a schema ID. */
@Serializable
data class PropertyRef(
    /** The schema ID to use. */
    @SerialName("\$ref") val metaRef: String? = null
)

/** Data class to represent media upload parameters for [SchemaMethod.mediaUpload]. */
@Serializable
data class SchemaMethodMediaUpload(
    /** MIME Media Ranges for acceptable media uploads to this method. */
    val accept: List<String>? = emptyList(),
    /** Maximum size of a media upload, such as "1MB", "2GB" or "3TB". */
    val maxSize: String? = null,
    /** Supported upload protocols. */
    val protocols: SchemaMethodMediaUploadProtocols? = null
)

/** Data class to represent media upload protocols for [SchemaMethodMediaUpload.protocols]. */
@Serializable
data class SchemaMethodMediaUploadProtocols(
    /** Supports uploading as a single HTTP request. */
    val simple: SchemaMethodMediaUploadProtocol? = null,
    /** Supports the Resumable Media Upload protocol. */
    val resumable: SchemaMethodMediaUploadProtocol? = null
)

/** Data class to represent an individual media upload protocol for [SchemaMethodMediaUploadProtocols]. */
@Serializable
data class SchemaMethodMediaUploadProtocol(
    /** True if this endpoint supports upload multipart media. */
    val multipart: Boolean? = true,
    /**
     * The URI path to be used for upload.
     * Should be used in conjunction with the [rootUrl][SchemaRes.rootUrl] property at the API-level.
     */
    val path: String? = null
)
