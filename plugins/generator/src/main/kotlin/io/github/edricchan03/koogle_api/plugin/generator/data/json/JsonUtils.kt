package io.github.edricchan03.koogle_api.plugin.generator.data.json

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

private fun Map<String, JsonElement>.toJsonObject() = JsonObject(this)

/**
 * Sorts the keys in this [JsonObject].
 * Implementation based on the
 * [API generator from the Node.js Google API client](https://github.com/googleapis/google-api-nodejs-client/blob/f841e1353cb8868a4503662a5fb2136b1b2b06b0/src/generator/download.ts#L138).
 * @return The sorted version of the [JsonObject]. Note that it does not mutate the original
 * value.
 */
public fun JsonObject.sortKeys(): JsonObject {
    // Sort the object by its keys in alphabetical order
    return toSortedMap()
        // Map the result to a mutable map
        .mapValuesTo(mutableMapOf()) { (_, value) ->
            // Recursively sort the keys if it's an object
            if (value is JsonObject) value.sortKeys() else value
        }.toJsonObject()
}
