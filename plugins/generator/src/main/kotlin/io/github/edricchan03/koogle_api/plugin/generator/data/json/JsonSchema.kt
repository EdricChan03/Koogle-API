package io.github.edricchan03.koogle_api.plugin.generator.data.json

import io.github.edricchan03.koogle_api.plugin.generator.data.json.serializer.JsonSchemaFormatSerializer
import io.github.edricchan03.koogle_api.plugin.generator.data.json.serializer.JsonSchemaListWrapSerializer
import io.github.edricchan03.koogle_api.plugin.generator.data.json.serializer.JsonSchemaTypeSetWrapSerializer
import io.github.edricchan03.koogle_api.plugin.generator.data.json.serializer.StringListWrapSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*

/**
 * Enum class representing the possible values for the
 * [`type` property](https://datatracker.ietf.org/doc/html/draft-zyp-json-schema-03#section-5.1).
 */
@Serializable
public enum class JsonSchemaType {
    /** Indicates that the value **must** be a string. */
    @SerialName("string")
    String,

    /** Indicates that the value **must** be a number. (Floating point numbers are allowed) */
    @SerialName("number")
    Number,

    /** Indicates that the value **must** be an integer. (No floating point numbers are allowed) */
    @SerialName("integer")
    Integer,

    /** Indicates that the value **must** be a boolean. */
    @SerialName("boolean")
    Boolean,

    /** Indicates that the value **must** be an object. */
    @SerialName("object")
    Object,

    /** Indicates that the value **must** be an array. */
    @SerialName("array")
    Array,

    /**
     * Indicates that the value **must** be `null`.
     * Note this is mainly for the purpose of
     * being able to use union types to define nullability.
     * If this type is not included in a union, null values are not allowed (the
     * primitives listed above do not allow nulls on their own).
     */
    @SerialName("null")
    Null,

    /** Indicates that the value **may** be of any type including `null`. */
    @SerialName("any")
    Any
}

/**
 * Class representing the possible values for the
 * [`format` property](https://datatracker.ietf.org/doc/html/draft-zyp-json-schema-03#section-5.23).
 * @property jsonValue The underlying JSON string representation.
 */
@Serializable(with = JsonSchemaFormatSerializer::class)
public sealed class JsonSchemaFormat(public open val jsonValue: String) {
    /**
     * This _should_ be a date in ISO 8601 format of `YYYY-MM-DDThh:mm:ssZ`
     * in UTC time.
     *
     * This is the recommended form of date/timestamp.
     */
    public object DateTime : JsonSchemaFormat("date-time")

    /**
     * This _should_ be a date in the format of `YYYY-MM-DD`.
     *
     * It is recommended that you use the [`date-time`][DateTime] format
     * instead of `date` unless you need to transfer only the date part.
     */
    public object Date : JsonSchemaFormat("date")

    /**
     * This _should_ be a time in the format of `hh:mm:ss`.
     *
     * It is recommended that you use the [`date-time`][DateTime] format
     * instead of `time` unless you need to transfer only the time part.
     */
    public object Time : JsonSchemaFormat("time")

    /**
     * This _should_ be the difference, measured in milliseconds, between the
     * specified time and midnight, 00:00 of January 1, 1970 UTC.
     *
     * The value _should_ be a number (integer or float).
     */
    public object UtcMillisec : JsonSchemaFormat("utc-millisec")

    /**
     * A regular expression, following the regular expression specification from
     * ECMA 262/Perl 5.
     */
    public object Regex : JsonSchemaFormat("regex")

    /** This is a CSS color (like "#FF0000" or "red"), based on CSS 2.1. */
    public object Color : JsonSchemaFormat("color")

    /**
     * This is a CSS style definition (like `color: red; background-color:#FFF"),
     * based on CSS 2.1.
     */
    public object Style : JsonSchemaFormat("style")

    /**
     * This _should_ be a phone number (format _may_ follow E.123).
     */
    public object Phone : JsonSchemaFormat("phone")

    /** This value _should_ be a URI. */
    public object Uri : JsonSchemaFormat("uri")

    /** This _should_ be an email address. */
    public object Email : JsonSchemaFormat("email")

    /** This _should_ be an ip version 4 address. */
    public object IpAddress : JsonSchemaFormat("ip-address")

    /** This _should_ be an ip version 6 address. */
    public object IpV6 : JsonSchemaFormat("ipv6")

    /** This _should_ be a host-name. */
    public object HostName : JsonSchemaFormat("host-name")

    /**
     * A custom format which _may_ be represented as an URI, and this URI _may_
     * reference a schema of that format.
     */
    public data class Custom(override val jsonValue: String) : JsonSchemaFormat(jsonValue)

    public companion object {
        public fun getFormat(value: String): JsonSchemaFormat = when (value) {
            "date-time" -> DateTime
            "date" -> Date
            "time" -> Time
            "utc-millisec" -> UtcMillisec
            "regex" -> Regex
            "color" -> Color
            "style" -> Style
            "phone" -> Phone
            "uri" -> Uri
            "email" -> Email
            "ip-address" -> IpAddress
            "ipv6" -> IpV6
            "host-name" -> HostName
            else -> Custom(value)
        }
    }
}

/**
 * Data class representing
 * [draft 3 of the JSON schema](https://datatracker.ietf.org/doc/html/draft-zyp-json-schema-03).
 * Note that this data class is not responsible for JSON schema validation.
 *
 * As per the [IETF proposal](https://datatracker.ietf.org/doc/html/draft-zyp-json-schema-03#section-5),
 * all properties are marked as optional.
 */
@Serializable
public data class JsonSchema(
    /**
     * This attribute defines what the primitive type or the schema of the
     * instance **must** be in order to validate.
     * This attribute can take one of two forms:
     *
     * - _Simple Types_: A string indicating a primitive or simple type.
     * - _Union Types_: An array of two or more simple type definitions.
     * Each item in the array MUST be a simple type definition or a schema.
     * The instance value is valid if it is of the same type as one of
     * the simple type definitions, or valid by one of the schemas, in
     * the array.
     *
     *    For example, a schema that defines if an instance can be a string or
     * a number would be:
     *    ```json
     *    {"type":["string","number"]}
     *    ```
     */
    @Serializable(with = JsonSchemaTypeSetWrapSerializer::class) val type: Set<JsonSchemaType>? = emptySet(),
    /**
     * This attribute is an object with property definitions that define the
     * valid values of instance object property values.
     *
     * When the instance value is an object, the property values of the instance
     * object **must** conform to the property definitions in this object.
     *
     * In this object, each property definition's value **must** be a schema, and
     * the property's name **must** be the name of the instance property that it defines.
     *
     * The instance property value **must** be valid, according to the schema from
     * the property definition.
     *
     * Properties are considered unordered, the order of the instance properties MAY be
     * in any order.
     */
    val properties: Map<String, JsonSchema>? = emptyMap(),
    /**
     * This attribute is an object that defines the schema for a set of
     * property names of an object instance.
     *
     * The name of each property of this attribute's object is a regular expression pattern
     * in the ECMA 262/Perl 5 format, while the value is a schema.
     *
     * If the pattern matches the name of a property on the instance object, the value of
     * the instance's property MUST be valid against the pattern name's schema value.
     */
    val patternProperties: Map<String, JsonSchema>? = emptyMap(),
    /**
     * This attribute defines a schema for all properties that are not
     * explicitly defined in an object-type definition.
     *
     * If specified, the value **must** be a schema or a boolean.
     *
     * If `false` is provided, no additional properties are allowed beyond the properties
     * defined in the schema.
     *
     * The default value is an empty schema which allows any value for additional properties.
     */
    val additionalProperties: JsonElement? = null,
    /**
     * This attribute defines the allowed items in an instance array, and
     * **must** be a schema or an array of schemas.
     *
     * The default value is an empty schema which allows any value for items in
     * the instance array.
     *
     * When this attribute value is a schema and the instance value is an
     * array, then all the items in the array MUST be valid, according to the schema.
     *
     * When this attribute value is an array of schemas and the instance
     * value is an array, each position in the instance array **must** conform
     * to the schema in the corresponding position for this array.
     * This is called tuple typing.
     *
     * When tuple typing is used, additional items are
     * allowed, disallowed, or constrained by the [additionalItems]
     * attribute using the same rules as [additionalProperties] for objects.
     */
    @Serializable(with = JsonSchemaListWrapSerializer::class) val items: List<JsonSchema>? = emptyList(),
    /**
     * This provides a definition for additional items in an array instance
     * when tuple definitions of the items are provided.
     *
     * This can be `false` to indicate additional items in the array are not allowed, or
     * it can be a schema that defines the schema of the additional items.
     */
    val additionalItems: JsonElement? = null,
    /**
     * This attribute indicates if the instance must have a value, and not
     * be undefined.
     *
     * This is `false` by default, making the instance optional.
     */
    val required: Boolean? = false,
    /**
     * This attribute is an object that defines the requirements of a
     * property on an instance object.
     *
     * If an object instance has a property with the same name as a property in this
     * attribute's object, then the instance must be valid against the attribute's
     * property value (hereafter referred to as the "dependency value").
     *
     * The dependency value can take one of two forms:
     *
     * - _Simple Dependency_: If the dependency value is a string, then the
     * instance object **must** have a property with the same name as the dependency
     * value.
     *
     *    If the dependency value is an array of strings, then the instance object
     *    **must** have a property with the same name as each string in the dependency
     *    value's array.
     * - _Schema Dependency_: If the dependency value is a schema, then the
     * instance object **must** be valid against the schema.
     */
    val dependencies: JsonElement? = null,
    /**
     * This attribute defines the minimum value of the instance property
     * when the type of the instance value is a number.
     */
    val minimum: Float? = null,
    /**
     * This attribute defines the maximum value of the instance property
     * when the type of the instance value is a number.
     */
    val maximum: Float? = null,
    /**
     * This attribute indicates if the value of the instance (if the
     * instance is a number) can not equal the number defined by the
     * "minimum" attribute.
     *
     * This is `false` by default, meaning the instance
     * value can be greater than or equal to the minimum value.
     */
    val exclusiveMinimum: Boolean? = false,
    /**
     * This attribute indicates if the value of the instance (if the
     * instance is a number) can not equal the number defined by the
     * "maximum" attribute.
     *
     * This is `false` by default, meaning the instance
     * value can be less than or equal to the maximum value.
     */
    val exclusiveMaximum: Boolean? = false,
    /**
     * This attribute defines the minimum number of values in an array when
     * the array is the instance value.
     */
    val minItems: Int? = 0,
    /**
     * This attribute defines the maximum number of values in an array when
     * the array is the instance value.
     */
    val maxItems: Int? = null,
    /**
     * This attribute indicates that all items in an array instance MUST be
     * unique (contains no two identical values).
     *
     * Two instances are considered equal if they are both of the same type and:
     * - are null; or
     * - are booleans/numbers/strings and have the same value; or
     * - are arrays, contains the same number of items, and each item in
     * the array is equal to the corresponding item in the other array; or
     * - are objects, contains the same property names, and each property
     * in the object is equal to the corresponding property in the other
     * object.
     */
    val uniqueItems: Boolean? = false,
    /**
     * When the instance value is a string, this provides a regular
     * expression that a string instance MUST match in order to be valid.
     *
     * Regular expressions _should_ follow the regular expression
     * specification from ECMA 262/Perl 5
     */
    val pattern: String? = null,
    /**
     * When the instance value is a string, this defines the minimum length
     * of the string.
     */
    val minLength: Int? = 0,
    /**
     * When the instance value is a string, this defines the maximum length
     * of the string.
     */
    val maxLength: Int? = null,
    /**
     * This provides an enumeration of all possible values that are valid
     * for the instance property.
     *
     * This **must** be an array, and each item in the array represents a possible
     * value for the instance value.
     *
     * If this attribute is defined, the instance value **must** be one of the
     * values in the array in order for the schema to be valid.
     *
     * Comparison of enum values uses the same algorithm as defined in [uniqueItems].
     */
    val enum: Set<String>? = emptySet(),
    /**
     * This attribute defines the default value of the instance when the
     * instance is undefined.
     */
    val default: JsonElement? = null,
    /**
     * This attribute is a string that provides a short description of the
     * instance property.
     */
    val title: String? = null,
    /**
     * This attribute is a string that provides a full description of the instance property.
     */
    val description: String? = null,
    /**
     * This property defines the type of data, content type, or microformat
     * to be expected in the instance property values.
     *
     * A format attribute _may_ be one of the values listed below, and if so,
     * _should_ adhere to the semantics describing for the format.
     *
     * A format _should_ only be used to give meaning to primitive types (string,
     * integer, number, or boolean).
     *
     * Validators _may_ (but are not required to) validate that the instance values
     * conform to a format.
     *
     * @see JsonSchemaFormat
     */
    val format: JsonSchemaFormat? = null,
    /**
     * This attribute defines what value the number instance must be divisible by
     * with no remainder (the result of the division must be an integer.)
     *
     * The value of this attribute SHOULD NOT be 0.
     */
    val divisibleBy: Float? = null,
    /**
     * This attribute takes the same values as the [type] attribute, however
     * if the instance matches the type or if this value is an array and the
     * instance matches any type or schema in the array, then this instance
     * is not valid.
     */
    @Serializable(with = JsonSchemaTypeSetWrapSerializer::class) val disallow: Set<JsonSchemaType>? = emptySet(),
    /**
     * The value of this property **must** be another schema which will provide
     * a base schema which the current schema will inherit from.
     *
     * The inheritance rules are such that any instance that is valid according
     * to the current schema **must** be valid according to the referenced
     * schema.
     *
     * This _may_ also be an array, in which case, the instance **must**
     * be valid for all the schemas in the array.
     *
     * A schema that extends another schema _may_ define additional attributes,
     * constrain existing attributes or add other constraints.
     *
     * Conceptually, the behavior of extends can be seen as validating an
     * instance against all constraints in the extending schema as well as
     * the extended schema(s).
     *
     * More optimized implementations that merge schemas are possible, but are not
     * required.
     *
     * An example of using `extends`:
     *
     * ```json
     * {
     *   "description":"An adult",
     *   "properties":{"age":{"minimum": 21}},
     *   "extends":"person"
     * }
     * ```
     * ```json
     * {
     *   "description":"Extended schema",
     *   "properties":{"deprecated":{"type": "boolean"}},
     *   "extends":"http://json-schema.org/draft-03/schema"
     * }
     * ```
     */
    @Serializable(with = StringListWrapSerializer::class) val extends: List<String>? = emptyList(),
    /**
     * This attribute defines the current URI of this schema (this attribute
     * is effectively a "self" link).
     *
     * This URI _may_ be relative or absolute.
     * If the URI is relative, it is resolved against the current URI of the
     * parent schema it is contained in.
     *
     * If this schema is not contained in
     * any parent schema, the current URI of the parent schema is held to be
     * the URI under which this schema was addressed.
     *
     * If id is missing, the
     * current URI of a schema is defined to be that of the parent schema.
     * The current URI of the schema is also used to construct relative
     * references such as for `$ref`.
     */
    val id: String? = null,
    /**
     * This attribute defines a URI of a schema that contains the full
     * representation of this schema.
     *
     * When a validator encounters this attribute, it _should_ replace the current
     * schema with the schema referenced by the value's URI (if known and available) and re-
     * validate the instance.
     *
     * This URI _may_ be relative or absolute, and relative URIs _should_ be resolved
     * against the URI of the current schema.
     *
     * (Implementation note: `$ref` is instead named `metaRef` internally to prevent the amount
     * of escaping required to escape the `$` character, which is normally reserved for template
     * strings and the like.)
     */
    @SerialName("\$ref") val metaRef: String? = null,
    /**
     *
     * This attribute defines a URI of a JSON Schema that is the schema of
     * the current schema.
     *
     * When this attribute is defined, a validator _should_ use the schema
     * referenced by the value's URI (if known and available) when resolving
     * Hyper Schema ([Section 6](https://datatracker.ietf.org/doc/html/draft-zyp-json-schema-03#section-6))
     * links ([Section 6.1](https://datatracker.ietf.org/doc/html/draft-zyp-json-schema-03#section-6.1)).
     *
     * A validator _may_ use this attribute's value to determine which version
     * of JSON Schema the current schema is written in, and provide the
     * appropriate validation features and behavior.
     *
     * Therefore, it is _recommended_ that all schema authors include this
     * attribute in their schemas to prevent conflicts with future JSON Schema
     * specification changes.
     *
     * (Implementation note: `$schema` is instead named `metaSchema` internally
     * to prevent the amount of escaping required to escape the `$` character,
     * which is normally reserved for template strings and the like.)
     */
    @SerialName("\$schema") val metaSchema: String? = null
) {
    /** Retrieves the [additional properties][additionalProperties] as a boolean. */
    val additionalPropertiesAsBoolean: Boolean? = additionalProperties?.jsonPrimitive?.booleanOrNull

    /**
     * Retrieves the [additional properties][additionalProperties] as an object.
     *
     * **Warning: This function does not check if the value is actually an object.**
     * @param json The [Json] instance to use.
     * @return The additional properties, converted to a [JsonSchema].
     */
    public fun getAdditionalPropertiesAsSchema(json: Json): Map<String, JsonSchema>? = additionalProperties?.let {
        json.decodeFromJsonElement<Map<String, JsonSchema>?>(it)
    }

    /** Retrieves the [additional items][additionalItems] as a boolean. */
    val additionalItemsAsBoolean: Boolean? = additionalItems?.jsonPrimitive?.booleanOrNull

    /**
     * Retrieves the [additional items][additionalItems] as an object.
     *
     * **Warning: This function does not check if the value is actually an object.**
     * @param json The [Json] instance to use.
     * @return The additional items, converted to a [JsonSchema].
     */
    public fun getAdditionalItemsAsSchema(json: Json): Map<String, JsonSchema>? = additionalItems?.let {
        json.decodeFromJsonElement<Map<String, JsonSchema>?>(it)
    }

    /** Retrieves the [dependencies] as a string. */
    val dependenciesAsString: String? = dependencies?.jsonPrimitive?.contentOrNull

    /** Retrieves the [dependencies] as a JSON array. */
    private val dependenciesAsJsonArray: JsonArray? = dependencies?.jsonArray

    /**
     * Retrieves the dependencies as a list of strings.
     * **Warning: This function does not check if the value is actually a list.**
     * @param json The [Json] instance to use.
     * @return The dependencies, converted to a list of strings.
     */
    public fun getDependenciesAsList(json: Json): List<String>? = dependenciesAsJsonArray?.let {
        json.decodeFromJsonElement<List<String>?>(it)
    }

    /**
     * Retrieves the dependencies as a [JsonSchema].
     * **Warning: This function does not check if the value is actually an object.**
     * @param json The [Json] instance to use.
     * @return The dependencies, converted to a [JsonSchema].
     */
    public fun getDependenciesAsSchema(json: Json): JsonSchema? = dependencies?.let {
        json.decodeFromJsonElement<JsonSchema?>(it)
    }
}
