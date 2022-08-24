package io.github.edricchan03.koogle_api.data.json.serializer

import io.github.edricchan03.koogle_api.data.json.JsonSchemaFormat
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/** Serializer to serialize/deserialize a [JsonSchemaFormat]. */
object JsonSchemaFormatSerializer : KSerializer<JsonSchemaFormat> {
    override val descriptor = PrimitiveSerialDescriptor("JsonSchemaFormat", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder) = JsonSchemaFormat.getFormat(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: JsonSchemaFormat) {
        encoder.encodeString(value.jsonValue)
    }
}
