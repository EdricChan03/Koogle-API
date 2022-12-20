package io.github.edricchan03.koogle_api.common.http.headers.delegate

import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValueProperty
import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValuePropertyProvider
import io.github.edricchan03.koogle_api.common.http.values.utils.minusAssign
import io.github.edricchan03.koogle_api.common.http.values.utils.set
import io.ktor.http.*
import io.ktor.util.*
import kotlin.reflect.KProperty

internal class ContentTypeHeaderDelegate(
    override val name: String
) : NamedValueProperty<List<ContentType>?> {
    override fun getValue(thisRef: StringValuesBuilder, property: KProperty<*>): List<ContentType>? =
        thisRef.getAll(name)?.map { ContentType.parse(it) }

    override fun setValue(thisRef: StringValuesBuilder, property: KProperty<*>, value: List<ContentType>?) {
        if (value == null) {
            thisRef -= name
        } else {
            thisRef[name] = value.map { it.toString() }
        }
    }
}

internal class ContentTypeDelegateProvider(
    private val name: String?
) : NamedValuePropertyProvider<List<ContentType>?> {
    override fun provideDelegate(
        thisRef: StringValuesBuilder, property: KProperty<*>
    ): NamedValueProperty<List<ContentType>?> {
        return ContentTypeHeaderDelegate(name ?: property.name)
    }
}
