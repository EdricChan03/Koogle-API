package io.github.edricchan03.koogle_api.data

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
enum class SchemaType {
    @SerialName("object") Object,
    @SerialName("integer") Integer,
    @SerialName("string") String,
    @SerialName("array") Array,
    @SerialName("boolean") Boolean
}

typealias SchemaResources = Map<String, SchemaResource>

@Serializable
data class SchemaResource(
    val methods: SchemaMethods? = null,
    val resources: SchemaResources? = null
)

typealias SchemaItems = Map<String, SchemaItem>

@Serializable
data class SchemaItem(
    val description: String? = null,
    val default: String? = null,
    val id: String? = null,
    val properties: SchemaItems? = null,
    val additionalProperties: SchemaItems? = null,
    val items: SchemaItems? = null,
    val type: SchemaType? = null,
    val format: String? = null,
    val `$ref`: String? = null
)

typealias SchemaParameters = Map<String, SchemaParameter>

@Serializable
data class SchemaParameter(
    val default: String,
    val description: String,
    val location: String,
    val enum: List<String>,
    val enumDescription: List<String>,
    val type: SchemaType,
    val format: String,
    val required: Boolean
)

typealias SchemaMethods = Map<String, SchemaMethod>

@Serializable
data class SchemaMethod(
    val description: String,
    val httpMethod: HttpMethod,
    val id: String,
    val parameterOrder: String? = null,
    val parameters: SchemaParameters? = null,
    val path: String,
    val request: PropertyRef,
    val response: PropertyRef,
    val sampleUrl: String,
    val scopes: List<String>,
    val fragment: String,
    val mediaUpload: SchemaMethodMediaUpload,
    val supportsMediaDownload: Boolean? = null
)

@Serializable
data class FragmentResponse(
    val codeFragment: Map<String, FragmentResponseFragment>
)

@Serializable
data class FragmentResponseFragment(
    val fragment: String
)

@Serializable
data class PropertyRef(
    val `$ref`: String
)

@Serializable
data class SchemaMethodMediaUpload(
    val protocols: SchemaMethodMediaUploadProtocols
)

@Serializable
data class SchemaMethodMediaUploadProtocols(
    val simple: SchemaMethodMediaUploadProtocolsSimple
)

@Serializable
data class SchemaMethodMediaUploadProtocolsSimple(
    val path: String
)
