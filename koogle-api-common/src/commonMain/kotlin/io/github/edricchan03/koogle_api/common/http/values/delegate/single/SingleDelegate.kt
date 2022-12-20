package io.github.edricchan03.koogle_api.common.http.values.delegate.single

import io.ktor.util.*
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

public typealias ValueListDelegate<T> = ReadWriteProperty<StringValuesBuilder, List<T>?>
public typealias ValueListDelegateProvider<T> = PropertyDelegateProvider<StringValuesBuilder, ValueListDelegate<T>>

public typealias ValueSingleDelegate<T> = ReadWriteProperty<StringValuesBuilder, T?>
public typealias ValueSingleDelegateProvider<T> = PropertyDelegateProvider<StringValuesBuilder, ValueSingleDelegate<T>>

public class SingleDelegate<T>(
    private val delegate: ValueListDelegate<T>
) : ValueSingleDelegate<T> {
    override fun getValue(thisRef: StringValuesBuilder, property: KProperty<*>): T? =
        delegate.getValue(thisRef, property)?.first()

    override fun setValue(thisRef: StringValuesBuilder, property: KProperty<*>, value: T?) {
        delegate.setValue(thisRef, property, listOfNotNull(value))
    }
}

public class SingleDelegateFactory<T>(
    private val provider: ValueListDelegateProvider<T>
) : ValueSingleDelegateProvider<T> {
    override fun provideDelegate(thisRef: StringValuesBuilder, property: KProperty<*>): ValueSingleDelegate<T> =
        SingleDelegate(
            delegate = provider.provideDelegate(thisRef, property)
        )
}

/** Specifies that this query parameter should only take a single value. */
public fun <T : Any> ValueListDelegate<T>.single(): ValueSingleDelegate<T> =
    SingleDelegate(this)

/** Specifies that this query parameter should only take a single value. */
public fun <T : Any> ValueListDelegateProvider<T>.single(): ValueSingleDelegateProvider<T> =
    SingleDelegateFactory(this)
