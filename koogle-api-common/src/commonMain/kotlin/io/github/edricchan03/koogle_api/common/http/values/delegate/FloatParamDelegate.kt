package io.github.edricchan03.koogle_api.common.http.values.delegate

import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValueProperty
import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValuePropertyProvider
import io.github.edricchan03.koogle_api.common.http.values.utils.minusAssign
import io.github.edricchan03.koogle_api.common.http.values.utils.set
import io.ktor.util.*
import kotlin.reflect.KProperty

/** Method to convert a [String] to its [Float] representation. */
public typealias StringToFloat = (String) -> Float?
/** Method to convert a [Float] to its [String] representation. */
public typealias FloatToString = (Float) -> String

public val defaultStringToFloat: StringToFloat = String::toFloatOrNull
public val defaultFloatToString: FloatToString = Float::toString

internal class FloatParamDelegate(
    override val name: String,
    private val toFloat: StringToFloat = defaultStringToFloat,
    private val toString: FloatToString = defaultFloatToString
) : NamedValueProperty<List<Float>?> {
    override fun getValue(thisRef: StringValuesBuilder, property: KProperty<*>): List<Float>? =
        thisRef.getAll(name)?.mapNotNull(toFloat)

    override fun setValue(thisRef: StringValuesBuilder, property: KProperty<*>, value: List<Float>?) {
        if (value == null) {
            thisRef -= name
        } else {
            thisRef[name] = value.map(toString)
        }
    }
}

internal class FloatDelegateProvider(
    private val name: String?,
    private val toFloat: StringToFloat = defaultStringToFloat,
    private val toString: FloatToString = defaultFloatToString
) : NamedValuePropertyProvider<List<Float>?> {
    override fun provideDelegate(
        thisRef: StringValuesBuilder, property: KProperty<*>
    ): NamedValueProperty<List<Float>?> {
        return FloatParamDelegate(name ?: property.name, toFloat, toString)
    }
}
