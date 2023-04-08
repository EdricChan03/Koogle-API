package io.github.edricchan03.koogle_api.plugin.generator.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Class mappings based from https://github.com/googleapis/nodejs-googleapis-common/blob/main/src/schema.ts

@Serializable
/**
 * Data class representing a
 * [resource from Google's Discovery API](https://developers.google.com/discovery/v1/reference/apis#resource-representations).
 */
public data class RestDescription(
    /** The kind for this response. */
    val kind: String = "discovery#restDescription",
    /** The ETag for this response. */
    // Note: This property is marked as readOnly in the schema
    val etag: String? = null,
    /** Indicate the version of the Discovery API used to generate this doc. */
    val discoveryVersion: String? = null,
    /** The ID of the Discovery document for the API. For example, `urlshortener:v1`. */
    val id: String? = null,
    /** The name of the API. For example, `urlshortener`. */
    val name: String? = null,
    /**
     * Indicates how the API name should be capitalized and split into various parts.
     * Useful for generating pretty class names.
     */
    val canonicalName: String? = null,
    /** The version of the API. For example, `v1`. */
    val version: String? = null,
    /** The revision of the API. */
    val revision: String? = null,
    /** The title of the API. For example, "Google Url Shortener API". */
    val title: String? = null,
    /** The description of this API. */
    val description: String? = null,
    /**
     * The domain of the owner of this API.
     * Together with the [ownerName] and a [packagePath] values, this can be used to generate a library for this
     * API which would have a unique fully qualified name.
     */
    val ownerDomain: String? = null,
    /** The name of the owner of this API. See [ownerDomain]. */
    val ownerName: String? = null,
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
    /** The package of the owner of this API. See [ownerDomain]. */
    val packagePath: String? = null,
    /** Common parameters that apply across all APIs. */
    val parameters: RestParameters? = null,
    /** Authentication information. */
    val auth: SchemaAuth? = null,
    /** A list of supported features for this API. */
    val features: List<String>? = emptyList(),
    /** The schemas for this API. */
    val schemas: RestItems? = null,
    /** API-level methods for this API. */
    val methods: RestMethods? = null,
    /** The resources in this API. */
    val resources: RestResources? = null,
    // Not much is known about this property...
    @SerialName("version_module")
    val versionModule: Boolean? = null,
    /** Enable exponential backoff for suitable methods in the generated clients. */
    val exponentialBackoffDefault: Boolean? = null,
)

/** Data class used to represent the [RestDescription.icons] data structure. */
@Serializable
public data class SchemaIcons(
    /** The URL of the 16x16 icon. */
    val x16: String? = null,
    /** The URL of the 32x32 icon. */
    val x32: String? = null
)
