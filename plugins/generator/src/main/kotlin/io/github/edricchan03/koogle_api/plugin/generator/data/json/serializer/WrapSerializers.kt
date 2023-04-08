package io.github.edricchan03.koogle_api.plugin.generator.data.json.serializer

import io.github.edricchan03.koogle_api.plugin.generator.data.json.JsonSchema
import io.github.edricchan03.koogle_api.plugin.generator.data.json.JsonSchemaType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.SetSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonTransformingSerializer

/**
 * Utility serializer that always wraps the specified data in a list if it is not already so.
 *
 * (Implementation based from the
 * [JSON transforming docs](https://github.com/Kotlin/kotlinx.serialization/blob/master/docs/json.md#array-wrapping)
 */
public open class ListWrapSerializer<T : Any>(elementSerializer: KSerializer<T>) :
    JsonTransformingSerializer<List<T>>(ListSerializer(elementSerializer)) {
    override fun transformDeserialize(element: JsonElement): JsonArray =
        if (element !is JsonArray) JsonArray(listOf(element)) else element
}

/**
 * Utility serializer that always wraps the specified data in a set if it is not already so.
 *
 * (Implementation based from the
 * [JSON transforming docs](https://github.com/Kotlin/kotlinx.serialization/blob/master/docs/json.md#array-wrapping)
 */
public open class SetWrapSerializer<T : Any>(elementSerializer: KSerializer<T>) :
    JsonTransformingSerializer<Set<T>>(SetSerializer(elementSerializer)) {
    override fun transformDeserialize(element: JsonElement): JsonArray =
        if (element !is JsonArray) JsonArray(listOf(element)) else element
}

// Internal implementations
internal object JsonSchemaListWrapSerializer :
    ListWrapSerializer<JsonSchema>(JsonSchema.serializer())

internal object JsonSchemaTypeSetWrapSerializer :
    SetWrapSerializer<JsonSchemaType>(JsonSchemaType.serializer())

internal object StringListWrapSerializer : ListWrapSerializer<String>(String.serializer())
