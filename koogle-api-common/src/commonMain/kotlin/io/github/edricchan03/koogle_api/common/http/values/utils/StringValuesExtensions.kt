package io.github.edricchan03.koogle_api.common.http.values.utils

import io.github.edricchan03.koogle_api.common.http.params.KoogleParametersBuilder
import io.ktor.util.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/** Sets the specified string-value. */
public operator fun StringValuesBuilder.set(name: String, values: Iterable<String>) {
    remove(name)
    appendAll(name, values)
}

/** Adds the specified string-value using `+=` syntax. */
public operator fun StringValuesBuilder.plusAssign(value: Pair<String, String>) {
    append(value.first, value.second)
}

/** Adds the specified string-value using `+=` syntax. */
public operator fun StringValuesBuilder.plusAssign(value: Pair<String, Iterable<String>>) {
    appendMissing(value.first, value.second)
}

/** Adds the specified string-value using `+=` syntax. */
public operator fun StringValuesBuilder.plusAssign(value: io.ktor.http.Parameters) {
    appendMissing(value)
}

/** Removes the specified string-value (by name) using `-=` syntax. */
public operator fun StringValuesBuilder.minusAssign(name: String) {
    remove(name)
}

/** Removes the specified string-value (by names) using `-=` syntax. */
public operator fun StringValuesBuilder.minusAssign(names: Iterable<String>) {
    names.forEach { remove(it) }
}

/** Removes the specified string-value (by property) using `-=` syntax. */
public operator fun StringValuesBuilder.minusAssign(property: KProperty<*>) {
    remove(property.name)
}

/** Removes the specified string-value (by properties) using `-=` syntax. */
public operator fun StringValuesBuilder.minusAssign(properties: Iterable<KProperty<*>>) {
    properties.forEach { remove(it.name) }
}

// Delegate functions

/**
 * Sets a string-value via the delegation pattern.
 * @param name The string-value's name, if any. By default, the property's name
 * will be used.
 * @param defaultValue The default value to set if the data set on this property is
 * `null`.
 * @param clearListOnSet Whether to remove the string-value from the list of parameters
 * before appending the new values when set.
 * When `false`, it would preserve any existing values for the string-value when new
 * values are set.
 */
public fun StringValuesBuilder.valueList(
    name: String? = null,
    defaultValue: List<String> = emptyList(),
    clearListOnSet: Boolean = true
): ReadWriteProperty<StringValuesBuilder, List<String>?> =
    object : ReadWriteProperty<StringValuesBuilder, List<String>?> {
        fun getName(property: KProperty<*>) = name ?: property.name
        override fun getValue(thisRef: StringValuesBuilder, property: KProperty<*>): List<String>? =
            thisRef.getAll(getName(property))

        override fun setValue(thisRef: StringValuesBuilder, property: KProperty<*>, value: List<String>?) {
            if (clearListOnSet) thisRef.remove(getName(property))
            thisRef.appendAll(getName(property), value ?: defaultValue)
        }

    }

/**
 * Sets a string-value via the delegation pattern.
 * @param name The string-value name, if any. By default, the property's name
 * will be used.
 * @param defaultValue The default value to set if the data set on this property is
 * `null`.
 * If both the data being set and [defaultValue] are `null`, the string-value
 * will be removed.
 */
public fun StringValuesBuilder.value(
    name: String? = null,
    defaultValue: String? = null
): ReadWriteProperty<KoogleParametersBuilder, String?> = object :
    ReadWriteProperty<StringValuesBuilder, String?> {
    fun getName(property: KProperty<*>) = name ?: property.name
    override fun getValue(thisRef: StringValuesBuilder, property: KProperty<*>): String? = thisRef[getName(property)]

    override fun setValue(thisRef: StringValuesBuilder, property: KProperty<*>, value: String?) {
        if ((value != null) || (defaultValue != null)) {
            thisRef[getName(property)] = requireNotNull(value ?: defaultValue)
        } else {
            // Remove the existing string-value
            thisRef -= getName(property)
        }
    }
}
