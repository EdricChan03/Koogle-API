package io.github.edricchan03.koogle_api.common.http.values.delegate.default

import io.ktor.util.*
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

public typealias OptionalStringValueDelegate<T> = ReadWriteProperty<StringValuesBuilder, T?>
public typealias OptionalStringValueDelegateProvider<T> = PropertyDelegateProvider<StringValuesBuilder, OptionalStringValueDelegate<T>>

public typealias DefaultStringValueDelegate<T> = ReadWriteProperty<StringValuesBuilder, T>
public typealias DefaultStringValueDelegateProvider<T> = PropertyDelegateProvider<StringValuesBuilder, DefaultStringValueDelegate<T>>

public class DelegateWithDefault<T>(
    private val delegate: OptionalStringValueDelegate<T>,
    private val defaultValue: T
) : DefaultStringValueDelegate<T> {
    override fun getValue(thisRef: StringValuesBuilder, property: KProperty<*>): T {
        return delegate.getValue(thisRef, property) ?: defaultValue
    }

    override fun setValue(thisRef: StringValuesBuilder, property: KProperty<*>, value: T) {
        delegate.setValue(thisRef, property, value)
    }
}

public class DelegateWithDefaultFactory<T>(
    private val provider: OptionalStringValueDelegateProvider<T>,
    private val defaultValue: T
) : DefaultStringValueDelegateProvider<T> {
    override fun provideDelegate(thisRef: StringValuesBuilder, property: KProperty<*>): DefaultStringValueDelegate<T> {
        return DelegateWithDefault(
            delegate = provider.provideDelegate(thisRef, property),
            defaultValue = defaultValue
        )
    }
}

/**
 * Adds a default value to the specified delegate. If the string-value has not been
 * set before, [defaultValue] is returned instead of `null`.
 */
public fun <T> OptionalStringValueDelegate<T>.withDefault(
    defaultValue: T
): DefaultStringValueDelegate<T> {
    return DelegateWithDefault(this, defaultValue)
}

/**
 * Adds a default value to the specified delegate. If the string-value has not been
 * set before, [defaultValue] is returned instead of `null`.
 */
public fun <T> OptionalStringValueDelegateProvider<T>.withDefault(
    defaultValue: T
): DefaultStringValueDelegateProvider<T> {
    return DelegateWithDefaultFactory(this, defaultValue)
}
