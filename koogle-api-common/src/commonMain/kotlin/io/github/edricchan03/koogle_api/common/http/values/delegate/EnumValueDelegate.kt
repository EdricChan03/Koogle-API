package io.github.edricchan03.koogle_api.common.http.values.delegate

import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValueProperty
import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValuePropertyProvider
import io.github.edricchan03.koogle_api.common.http.values.utils.minusAssign
import io.github.edricchan03.koogle_api.common.http.values.utils.set
import io.ktor.util.*
import kotlin.reflect.KProperty

internal class EnumValueDelegate<E : Enum<E>>(
    override val name: String,
    private val toEnum: (String) -> E?,
    private val toString: (E) -> String = { it.toString() }
) : NamedValueProperty<List<E>?> {
    override fun getValue(thisRef: StringValuesBuilder, property: KProperty<*>): List<E>? =
        thisRef.getAll(name)?.mapNotNull(toEnum)

    override fun setValue(thisRef: StringValuesBuilder, property: KProperty<*>, value: List<E>?) {
        if (value == null) {
            thisRef -= name
        } else {
            thisRef[name] = value.map { it.toString() }
        }
    }
}

public class EnumDelegateProvider<E : Enum<E>>(
    private val name: String?,
    private val toEnum: (String) -> E?,
    private val toString: (E) -> String = { it.toString() }
) : NamedValuePropertyProvider<List<E>?> {
    override fun provideDelegate(
        thisRef: StringValuesBuilder, property: KProperty<*>
    ): NamedValueProperty<List<E>?> {
        return EnumValueDelegate(name ?: property.name, toEnum, toString)
    }
}
