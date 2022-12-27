package io.github.edricchan03.koogle_api.common.http.values

import io.github.edricchan03.koogle_api.common.http.values.delegate.*
import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValuePropertyProvider
import io.github.edricchan03.koogle_api.common.http.values.delegate.single.ValueSingleDelegateProvider
import io.github.edricchan03.koogle_api.common.http.values.delegate.single.single
import io.ktor.util.*

/**
 * Creates an optional parameter of type `List<Boolean>` with the given [name] in this
 * [StringValuesBuilder] instance.
 *
 * If no [name] is provided, the property's name will be used.
 *
 * @param toBoolean Method to use when converting from its [String] form to [Boolean].
 * @param toString Method to use when converting from its [Boolean] form to [String].
 */
public fun StringValuesBuilder.booleanValues(
    name: String? = null,
    toBoolean: StringToBoolean = defaultStringToBoolean,
    toString: BooleanToString = defaultBooleanToString
): NamedValuePropertyProvider<List<Boolean>?> = BooleanDelegateProvider(name, toBoolean, toString)

/**
 * Creates an optional parameter of type [Boolean] with the given [name] in this
 * [StringValuesBuilder] instance.
 *
 * If no [name] is provided, the property's name will be used.
 *
 * @param toBoolean Method to use when converting from its [String] form to [Boolean].
 * @param toString Method to use when converting from its [Boolean] form to [String].
 */
public fun StringValuesBuilder.booleanValue(
    name: String? = null,
    toBoolean: StringToBoolean = defaultStringToBoolean,
    toString: BooleanToString = defaultBooleanToString
): ValueSingleDelegateProvider<Boolean?> = booleanValues(name, toBoolean, toString).single()

/**
 * Creates an optional parameter of type `List<Byte>` with the given [name] in this
 * [StringValuesBuilder] instance.
 *
 * If no [name] is provided, the property's name will be used.
 *
 * @param toByte Method to use when converting from its [String] form to [Byte].
 * @param toString Method to use when converting from its [Byte] form to [String].
 */
public fun StringValuesBuilder.byteValues(
    name: String? = null,
    toByte: StringToByte = defaultStringToByte,
    toString: ByteToString = defaultByteToString
): NamedValuePropertyProvider<List<Byte>?> = ByteDelegateProvider(name, toByte, toString)

/**
 * Creates an optional parameter of type [Byte] with the given [name] in this
 * [StringValuesBuilder] instance.
 *
 * If no [name] is provided, the property's name will be used.
 *
 * @param toByte Method to use when converting from its [String] form to [Byte].
 * @param toString Method to use when converting from its [Byte] form to [String].
 */
public fun StringValuesBuilder.byteValue(
    name: String? = null,
    toByte: StringToByte = defaultStringToByte,
    toString: ByteToString = defaultByteToString
): ValueSingleDelegateProvider<Byte?> = byteValues(name, toByte, toString).single()

/**
 * Creates an optional parameter of type `List<Double>` with the given [name] in this
 * [StringValuesBuilder] instance.
 *
 * If no [name] is provided, the property's name will be used.
 *
 * @param toDouble Method to use when converting from its [String] form to [Double].
 * @param toString Method to use when converting from its [Double] form to [String].
 */
public fun StringValuesBuilder.doubleValues(
    name: String? = null,
    toDouble: StringToDouble = defaultStringToDouble,
    toString: DoubleToString = defaultDoubleToString
): NamedValuePropertyProvider<List<Double>?> = DoubleDelegateProvider(name, toDouble, toString)

/**
 * Creates an optional parameter of type [Double] with the given [name] in this
 * [StringValuesBuilder] instance.
 *
 * If no [name] is provided, the property's name will be used.
 *
 * @param toDouble Method to use when converting from its [String] form to [Double].
 * @param toString Method to use when converting from its [Double] form to [String].
 */
public fun StringValuesBuilder.doubleValue(
    name: String? = null,
    toDouble: StringToDouble = defaultStringToDouble,
    toString: DoubleToString = defaultDoubleToString
): ValueSingleDelegateProvider<Double?> = doubleValues(name, toDouble, toString).single()

/**
 * Creates an optional parameter of type `List<Float>` with the given [name] in this
 * [StringValuesBuilder] instance.
 *
 * If no [name] is provided, the property's name will be used.
 *
 * @param toFloat Method to use when converting from its [String] form to [Float].
 * @param toString Method to use when converting from its [Float] form to [String].
 */
public fun StringValuesBuilder.floatValues(
    name: String? = null,
    toFloat: StringToFloat = defaultStringToFloat,
    toString: FloatToString = defaultFloatToString
): NamedValuePropertyProvider<List<Float>?> = FloatDelegateProvider(name, toFloat, toString)

/**
 * Creates an optional parameter of type [Float] with the given [name] in this
 * [StringValuesBuilder] instance.
 *
 * If no [name] is provided, the property's name will be used.
 *
 * @param toFloat Method to use when converting from its [String] form to [Float].
 * @param toString Method to use when converting from its [Float] form to [String].
 */
public fun StringValuesBuilder.floatValue(
    name: String? = null,
    toFloat: StringToFloat = defaultStringToFloat,
    toString: FloatToString = defaultFloatToString
): ValueSingleDelegateProvider<Float?> = floatValues(name, toFloat, toString).single()

/**
 * Creates an optional parameter of type `List<Int>` with the given [name] in this
 * [StringValuesBuilder] instance.
 *
 * If no [name] is provided, the property's name will be used.
 *
 * @param toInt Method to use when converting from its [String] form to [Int].
 * @param toString Method to use when converting from its [Int] form to [String].
 */
public fun StringValuesBuilder.intValues(
    name: String? = null,
    toInt: StringToInt = defaultStringToInt,
    toString: IntToString = defaultIntToString
): NamedValuePropertyProvider<List<Int>?> = IntDelegateProvider(name, toInt, toString)

/**
 * Creates an optional parameter of type [Int] with the given [name] in this
 * [StringValuesBuilder] instance.
 *
 * If no [name] is provided, the property's name will be used.
 *
 * @param toInt Method to use when converting from its [String] form to [Int].
 * @param toString Method to use when converting from its [Int] form to [String].
 */
public fun StringValuesBuilder.intValue(
    name: String? = null,
    toInt: StringToInt = defaultStringToInt,
    toString: IntToString = defaultIntToString
): ValueSingleDelegateProvider<Int?> = intValues(name, toInt, toString).single()

/**
 * Creates an optional parameter of type `List<Long>` with the given [name] in this
 * [StringValuesBuilder] instance.
 *
 * If no [name] is provided, the property's name will be used.
 *
 * @param toLong Method to use when converting from its [String] form to [Long].
 * @param toString Method to use when converting from its [Long] form to [String].
 */
public fun StringValuesBuilder.longValues(
    name: String? = null,
    toLong: StringToLong = defaultStringToLong,
    toString: LongToString = defaultLongToString
): NamedValuePropertyProvider<List<Long>?> = LongDelegateProvider(name, toLong, toString)

/**
 * Creates an optional parameter of type [Long] with the given [name] in this
 * [StringValuesBuilder] instance.
 *
 * If no [name] is provided, the property's name will be used.
 *
 * @param toLong Method to use when converting from its [String] form to [Long].
 * @param toString Method to use when converting from its [Long] form to [String].
 */
public fun StringValuesBuilder.longValue(
    name: String? = null,
    toLong: StringToLong = defaultStringToLong,
    toString: LongToString = defaultLongToString
): ValueSingleDelegateProvider<Long?> = longValues(name, toLong, toString).single()

/**
 * Creates an optional parameter of type `List<Short>` with the given [name] in this
 * [StringValuesBuilder] instance.
 *
 * If no [name] is provided, the property's name will be used.
 *
 * @param toShort Method to use when converting from its [String] form to [Short].
 * @param toString Method to use when converting from its [Short] form to [String].
 */
public fun StringValuesBuilder.shortValues(
    name: String? = null,
    toShort: StringToShort = defaultStringToShort,
    toString: ShortToString = defaultShortToString
): NamedValuePropertyProvider<List<Short>?> = ShortDelegateProvider(name, toShort, toString)

/**
 * Creates an optional parameter of type [Short] with the given [name] in this
 * [StringValuesBuilder] instance.
 *
 * If no [name] is provided, the property's name will be used.
 *
 * @param toShort Method to use when converting from its [String] form to [Short].
 * @param toString Method to use when converting from its [Short] form to [String].
 */
public fun StringValuesBuilder.shortValue(
    name: String? = null,
    toShort: StringToShort = defaultStringToShort,
    toString: ShortToString = defaultShortToString
): ValueSingleDelegateProvider<Short?> = shortValues(name, toShort, toString).single()

/**
 * Creates an optional parameter of type `List<String>` with the given [name] in this
 * [StringValuesBuilder] instance.
 *
 * If no [name] is provided, the property's name will be used.
 *
 * @param toParsedString Method to use when converting from its query parameter [String] form to
 * a parsed [String].
 * @param toParamString Method to use when converting from its parsed [String] form to its
 * query parameter [String] form.
 */
public fun StringValuesBuilder.stringValues(
    name: String? = null,
    toParsedString: StringParamToString = defaultStringParamToString,
    toParamString: StringToStringParam = defaultStringToStringParam
): NamedValuePropertyProvider<List<String>?> = StringDelegateProvider(name, toParsedString, toParamString)

/**
 * Creates an optional parameter of type [String] with the given [name] in this
 * [StringValuesBuilder] instance.
 *
 * If no [name] is provided, the property's name will be used.
 *
 * @param toParsedString Method to use when converting from its query parameter [String] form to
 * a parsed [String].
 * @param toParamString Method to use when converting from its parsed [String] form to its
 * query parameter [String] form.
 */
public fun StringValuesBuilder.stringValue(
    name: String? = null,
    toParsedString: StringParamToString = defaultStringParamToString,
    toParamString: StringToStringParam = defaultStringToStringParam
): ValueSingleDelegateProvider<String?> = stringValues(name, toParsedString, toParamString).single()


// Special types
/**
 * Creates an optional parameter of type `List<E>` with the given [name] in this
 * [StringValuesBuilder] instance.
 *
 * If no [name] is provided, the property's name will be used.
 *
 * @param toEnum Method to use when converting from its [String] form to [Enum].
 * @param toString Method to use when converting from its [Enum] form to [String].
 */
public inline fun <reified E : Enum<E>> StringValuesBuilder.enumValues(
    name: String? = null,
    noinline toEnum: (String) -> E? = { enumValueOf<E>(it) },
    noinline toString: (E) -> String = { it.toString() }
): NamedValuePropertyProvider<List<E>?> = EnumDelegateProvider(name, toEnum, toString)

/**
 * Creates an optional parameter of type [E] with the given [name] in this
 * [StringValuesBuilder] instance.
 *
 * If no [name] is provided, the property's name will be used.
 *
 * @param toEnum Method to use when converting from its [String] form to [Enum].
 * @param toString Method to use when converting from its [Enum] form to [String].
 */
public inline fun <reified E : Enum<E>> StringValuesBuilder.enumValue(
    name: String? = null,
    noinline toEnum: (String) -> E? = { enumValueOf<E>(it) },
    noinline toString: (E) -> String = { it.toString() }
): ValueSingleDelegateProvider<E?> = enumValues(name, toEnum, toString).single()
