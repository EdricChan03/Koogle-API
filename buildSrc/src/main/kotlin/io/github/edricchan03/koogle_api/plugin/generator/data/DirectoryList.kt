package io.github.edricchan03.koogle_api.plugin.generator.data

import kotlinx.serialization.Serializable

/**
 * JSON as returned by the Google API `discovery.apis.list` method.
 *
 * See https://developers.google.com/discovery/v1/reference/apis/list#response for more info
 */
@Serializable
data class DirectoryList(
    /** The fixed string `discovery#directoryList`. */
    val kind: String = "discovery#directoryList",
    /** Indicate the version of the Discovery API used to generate this doc. */
    val discoveryVersion: String,
    /** The individual directory entries. One entry per API/version pair. */
    val items: List<DirectoryItem>
)

/** Data class to represent an individual directory item. */
@Serializable
data class DirectoryItem(
    /** The kind for this response. */
    val kind: String,
    /** The ID of this API. */
    val id: String,
    /** The name of this API. */
    val name: String,
    /** The version of this API. */
    val version: String,
    /** The title of this API. */
    val title: String,
    /** The description of this API. */
    val description: String,
    /** The URL for the discovery REST document. */
    val discoveryRestUrl: String,
    /** A link to the discovery document. */
    // The discovery link only appears for some items as of this writing
    val discoveryLink: String? = null,
    /** Links to 16x16 and 32x32 icons representing the API. */
    val icons: SchemaIcons,
    /** A link to human readable documentation for the API. */
    // The documentation link isn't set for some items
    val documentationLink: String? = null,
    /** Labels for the status of this API, such as `limited_availability` or `deprecated`. */
    // Labels don't actually exist for any of the items currently, but we're leaving it here for now
    val labels: List<String> = listOf(),
    /** `true` if this version is the preferred version to use. */
    val preferred: Boolean
)
