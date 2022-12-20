package io.github.edricchan03.koogle_api.common.http.values.delegate

import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValueProperty
import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValuePropertyProvider
import io.github.edricchan03.koogle_api.common.http.values.utils.minusAssign
import io.github.edricchan03.koogle_api.common.http.values.utils.set
import io.ktor.util.*
import kotlin.reflect.KProperty

/** Method to convert a [String] (query parameter's value) to its [String] representation. */
public typealias StringParamToString = (String) -> String?
/** Method to convert a [String] to its [String] (query parameter's value) representation. */
public typealias StringToStringParam = (String) -> String

public val defaultStringParamToString: StringParamToString = { it }
public val defaultStringToStringParam: StringToStringParam = { it }

internal class StringParamDelegate(
    override val name: String,
    private val toParsedString: StringParamToString = defaultStringParamToString,
    private val toParamString: StringToStringParam = defaultStringToStringParam
) : NamedValueProperty<List<String>?> {
    override fun getValue(thisRef: StringValuesBuilder, property: KProperty<*>): List<String>? =
        thisRef.getAll(name)?.mapNotNull(toParsedString)

    override fun setValue(thisRef: StringValuesBuilder, property: KProperty<*>, value: List<String>?) {
        if (value == null) {
            thisRef -= name
        } else {
            thisRef[name] = value.map(toParamString)
        }
    }
}

internal class StringDelegateProvider(
    private val name: String?,
    private val toParsedString: StringParamToString = defaultStringParamToString,
    private val toParamString: StringToStringParam = defaultStringToStringParam
) : NamedValuePropertyProvider<List<String>?> {
    override fun provideDelegate(
        thisRef: StringValuesBuilder, property: KProperty<*>
    ): NamedValueProperty<List<String>?> {
        return StringParamDelegate(name ?: property.name, toParsedString, toParamString)
    }
}
