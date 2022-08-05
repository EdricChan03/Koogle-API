package io.github.edricchan03.koogle_api.data

import kotlinx.serialization.Serializable

/** JSON as returned by the Google API `discovery.apis.list` method. */
@Serializable
data class RootSchemas(
    val discoveryVersion: String,
    val kind: String,
    val items: List<RootSchema>
)

@Serializable
data class RootSchema(
    val kind: String,
    val id: String,
    val name: String,
    val version: String,
    val title: String,
    val description: String,
    val discoveryRestUrl: String,
    // The discovery link only appears for some items as of this writing
    val discoveryLink: String? = null,
    val icons: SchemaIcons,
    // The documentation link isn't set for some items
    val documentationLink: String? = null,
    // Labels don't actually exist for any of the items currently, but we're leaving it here for now
    val labels: List<String> = listOf(),
    val preferred: Boolean
)
