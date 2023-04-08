package io.github.edricchan03.koogle_api.plugin.generator.data.json.serializer

import io.github.edricchan03.koogle_api.plugin.generator.data.json.JsonSchemaFormat
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/** Serializer to serialize/deserialize a [JsonSchemaFormat]. */
public object JsonSchemaFormatSerializer : KSerializer<JsonSchemaFormat> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        "JsonSchemaFormat", PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): JsonSchemaFormat =
        JsonSchemaFormat.getFormat(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: JsonSchemaFormat) {
        encoder.encodeString(value.jsonValue)
    }
}
