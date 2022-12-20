package io.github.edricchan03.koogle_api.common.http.values.delegate

import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValueProperty
import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValuePropertyProvider
import io.github.edricchan03.koogle_api.common.http.values.utils.minusAssign
import io.github.edricchan03.koogle_api.common.http.values.utils.set
import io.ktor.util.*
import kotlin.reflect.KProperty

/** Method to convert a [String] to its [Long] representation. */
public typealias StringToLong = (String) -> Long?
/** Method to convert a [Long] to its [String] representation. */
public typealias LongToString = (Long) -> String

public val defaultStringToLong: StringToLong = String::toLongOrNull
public val defaultLongToString: LongToString = Long::toString


internal class LongParamDelegate(
    override val name: String,
    private val toLong: StringToLong = defaultStringToLong,
    private val toString: LongToString = defaultLongToString
) : NamedValueProperty<List<Long>?> {
    override fun getValue(thisRef: StringValuesBuilder, property: KProperty<*>): List<Long>? =
        thisRef.getAll(name)?.mapNotNull(toLong)

    override fun setValue(thisRef: StringValuesBuilder, property: KProperty<*>, value: List<Long>?) {
        if (value == null) {
            thisRef -= name
        } else {
            thisRef[name] = value.map(toString)
        }
    }
}

internal class LongDelegateProvider(
    private val name: String?,
    private val toLong: StringToLong = defaultStringToLong,
    private val toString: LongToString = defaultLongToString
) : NamedValuePropertyProvider<List<Long>?> {
    override fun provideDelegate(
        thisRef: StringValuesBuilder, property: KProperty<*>
    ): NamedValueProperty<List<Long>?> {
        return LongParamDelegate(name ?: property.name, toLong, toString)
    }
}
