package io.github.edricchan03.koogle_api.common.http.values.delegate

import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValueProperty
import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValuePropertyProvider
import io.github.edricchan03.koogle_api.common.http.values.utils.minusAssign
import io.github.edricchan03.koogle_api.common.http.values.utils.set
import io.ktor.util.*
import kotlin.reflect.KProperty

/** Method to convert a [String] to its [Int] representation. */
public typealias StringToInt = (String) -> Int?
/** Method to convert a [Int] to its [String] representation. */
public typealias IntToString = (Int) -> String

public val defaultStringToInt: StringToInt = String::toIntOrNull
public val defaultIntToString: IntToString = Int::toString

internal class IntParamDelegate(
    override val name: String,
    private val toInt: StringToInt = defaultStringToInt,
    private val toString: IntToString = defaultIntToString
) : NamedValueProperty<List<Int>?> {
    override fun getValue(thisRef: StringValuesBuilder, property: KProperty<*>): List<Int>? =
        thisRef.getAll(name)?.mapNotNull(toInt)

    override fun setValue(thisRef: StringValuesBuilder, property: KProperty<*>, value: List<Int>?) {
        if (value == null) {
            thisRef -= name
        } else {
            thisRef[name] = value.map(toString)
        }
    }
}

internal class IntDelegateProvider(
    private val name: String?,
    private val toInt: StringToInt = defaultStringToInt,
    private val toString: IntToString = defaultIntToString
) : NamedValuePropertyProvider<List<Int>?> {
    override fun provideDelegate(
        thisRef: StringValuesBuilder, property: KProperty<*>
    ): NamedValueProperty<List<Int>?> {
        return IntParamDelegate(name ?: property.name, toInt, toString)
    }
}
