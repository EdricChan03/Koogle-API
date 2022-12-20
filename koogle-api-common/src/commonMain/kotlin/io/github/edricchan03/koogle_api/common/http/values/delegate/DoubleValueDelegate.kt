package io.github.edricchan03.koogle_api.common.http.values.delegate

import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValueProperty
import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValuePropertyProvider
import io.github.edricchan03.koogle_api.common.http.values.utils.minusAssign
import io.github.edricchan03.koogle_api.common.http.values.utils.set
import io.ktor.util.*
import kotlin.reflect.KProperty

/** Method to convert a [String] to its [Double] representation. */
public typealias StringToDouble = (String) -> Double?
/** Method to convert a [Double] to its [String] representation. */
public typealias DoubleToString = (Double) -> String

public val defaultStringToDouble: StringToDouble = String::toDoubleOrNull
public val defaultDoubleToString: DoubleToString = Double::toString

internal class DoubleValueDelegate(
    override val name: String,
    private val toDouble: StringToDouble = defaultStringToDouble,
    private val toString: DoubleToString = defaultDoubleToString
) : NamedValueProperty<List<Double>?> {
    override fun getValue(thisRef: StringValuesBuilder, property: KProperty<*>): List<Double>? =
        thisRef.getAll(name)?.mapNotNull(toDouble)

    override fun setValue(thisRef: StringValuesBuilder, property: KProperty<*>, value: List<Double>?) {
        if (value == null) {
            thisRef -= name
        } else {
            thisRef[name] = value.map(toString)
        }
    }
}

internal class DoubleDelegateProvider(
    private val name: String?,
    private val toDouble: StringToDouble = defaultStringToDouble,
    private val toString: DoubleToString = defaultDoubleToString
) : NamedValuePropertyProvider<List<Double>?> {
    override fun provideDelegate(
        thisRef: StringValuesBuilder,
        property: KProperty<*>
    ): NamedValueProperty<List<Double>?> {
        return DoubleValueDelegate(name ?: property.name)
    }
}
