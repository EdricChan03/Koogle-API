package io.github.edricchan03.koogle_api.data

import kotlinx.serialization.Serializable

// Class mappings based from https://github.com/googleapis/nodejs-googleapis-common/blob/main/src/schema.ts

@Serializable
/**
 * Data class representing a
 * [resource from Google's Discovery API](https://developers.google.com/discovery/v1/reference/apis#resource-representations).
 */
data class SchemaRes(
    /** The kind for this response. */
    val kind: String = "discovery#restDescription",
    /** Indicate the version of the Discovery API used to generate this doc. */
    val discoveryVersion: String? = null,
    /** The ID of the Discovery document for the API. For example, `urlshortener:v1`. */
    val id: String? = null,
    /** The name of the API. For example, `urlshortener`. */
    val name: String? = null,
    /** The canonical name of the API. For example, "Url Shortener". */
    val canonicalName: String? = null,
    /** The version of the API. For example, `v1`. */
    val version: String? = null,
    /** The revision of the API. */
    val revision: String? = null,
    /** The title of the API. For example, "Google Url Shortener API". */
    val title: String? = null,
    /** The description of this API. */
    val description: String? = null,
    /** Links to 16x16 and 32x32 icons representing the API. */
    val icons: SchemaIcons? = null,
    /** A link to human-readable documentation for the API. */
    val documentationLink: String? = null,
    /** Labels for the status of this API. Valid values include `limited_availability` or `deprecated`. */
    val labels: List<String>? = emptyList(),
    /** The protocol described by the document. For example, REST. */
    val protocol: String? = null,
    /** **&#91;DEPRECATED&#93;** The base URL for REST requests. */
    val baseUrl: String? = null,
    /** **&#91;DEPRECATED&#93;** The base path for REST requests. */
    val basePath: String? = null,
    /** The root URL under which all API services live. */
    val rootUrl: String? = null,
    /** The base path for all REST requests. */
    val servicePath: String? = null,
    /** The path for REST batch requests. */
    val batchPath: String? = null,
    /** Common parameters that apply across all APIs. */
    val parameters: SchemaParameters? = null,
    /** Authentication information. */
    val auth: SchemaAuth? = null,
    /** A list of supported features for this API. */
    val features: List<String>? = emptyList(),
    /** The schemas for this API. */
    val schemas: SchemaItems? = null,
    /** API-level methods for this API. */
    val methods: SchemaMethods? = null,
    /** The resources in this API. */
    val resources: SchemaResources? = null,

)

/** Data class used to represent the [SchemaRes.icons] data structure. */
@Serializable
data class SchemaIcons(
    /** The URL of the 16x16 icon. */
    val x16: String,
    /** The URL of the 32x32 icon. */
    val x32: String
)
