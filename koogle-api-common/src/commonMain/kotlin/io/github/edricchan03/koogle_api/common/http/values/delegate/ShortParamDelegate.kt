package io.github.edricchan03.koogle_api.common.http.values.delegate

import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValueProperty
import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValuePropertyProvider
import io.github.edricchan03.koogle_api.common.http.values.utils.minusAssign
import io.github.edricchan03.koogle_api.common.http.values.utils.set
import io.ktor.util.*
import kotlin.reflect.KProperty

/** Method to convert a [String] to its [Short] representation. */
public typealias StringToShort = (String) -> Short?
/** Method to convert a [Short] to its [String] representation. */
public typealias ShortToString = (Short) -> String

public val defaultStringToShort: StringToShort = String::toShortOrNull
public val defaultShortToString: ShortToString = Short::toString

internal class ShortParamDelegate(
    override val name: String,
    private val toShort: StringToShort = defaultStringToShort,
    private val toString: ShortToString = defaultShortToString
) : NamedValueProperty<List<Short>?> {
    override fun getValue(thisRef: StringValuesBuilder, property: KProperty<*>): List<Short>? =
        thisRef.getAll(name)?.mapNotNull(toShort)

    override fun setValue(thisRef: StringValuesBuilder, property: KProperty<*>, value: List<Short>?) {
        if (value == null) {
            thisRef -= name
        } else {
            thisRef[name] = value.map(toString)
        }
    }
}

internal class ShortDelegateProvider(
    private val name: String?,
    private val toShort: StringToShort = defaultStringToShort,
    private val toString: ShortToString = defaultShortToString
) : NamedValuePropertyProvider<List<Short>?> {
    override fun provideDelegate(
        thisRef: StringValuesBuilder, property: KProperty<*>
    ): NamedValueProperty<List<Short>?> {
        return ShortParamDelegate(name ?: property.name, toShort, toString)
    }
}
