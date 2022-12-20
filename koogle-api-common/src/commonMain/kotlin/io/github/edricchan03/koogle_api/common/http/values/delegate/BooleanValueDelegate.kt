package io.github.edricchan03.koogle_api.common.http.values.delegate

import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValueProperty
import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValuePropertyProvider
import io.github.edricchan03.koogle_api.common.http.values.utils.minusAssign
import io.github.edricchan03.koogle_api.common.http.values.utils.set
import io.ktor.util.*
import kotlin.reflect.KProperty

/** Method to convert a [String] to its [Boolean] representation. */
public typealias StringToBoolean = (String) -> Boolean?
/** Method to convert a [Boolean] to its [String] representation. */
public typealias BooleanToString = (Boolean) -> String

public val defaultStringToBoolean: StringToBoolean = String::toBooleanStrictOrNull
public val defaultBooleanToString: BooleanToString = Boolean::toString

internal class BooleanValueDelegate(
    override val name: String,
    private val toBoolean: StringToBoolean = defaultStringToBoolean,
    private val toString: BooleanToString = defaultBooleanToString
) : NamedValueProperty<List<Boolean>?> {
    override fun getValue(thisRef: StringValuesBuilder, property: KProperty<*>): List<Boolean>? =
        thisRef.getAll(name)?.mapNotNull(toBoolean)

    override fun setValue(thisRef: StringValuesBuilder, property: KProperty<*>, value: List<Boolean>?) {
        if (value == null) {
            thisRef -= name
        } else {
            thisRef[name] = value.map(toString)
        }
    }
}

internal class BooleanDelegateProvider(
    private val name: String?,
    private val toBoolean: StringToBoolean = defaultStringToBoolean,
    private val toString: BooleanToString = defaultBooleanToString
) : NamedValuePropertyProvider<List<Boolean>?> {
    override fun provideDelegate(
        thisRef: StringValuesBuilder, property: KProperty<*>
    ): NamedValueProperty<List<Boolean>?> {
        return BooleanValueDelegate(name ?: property.name, toBoolean, toString)
    }
}
