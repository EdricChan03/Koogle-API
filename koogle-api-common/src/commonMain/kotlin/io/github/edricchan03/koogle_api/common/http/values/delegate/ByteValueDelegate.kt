package io.github.edricchan03.koogle_api.common.http.values.delegate

import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValueProperty
import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValuePropertyProvider
import io.github.edricchan03.koogle_api.common.http.values.utils.minusAssign
import io.github.edricchan03.koogle_api.common.http.values.utils.set
import io.ktor.util.*
import kotlin.reflect.KProperty

/** Method to convert a [String] to its [Byte] representation. */
public typealias StringToByte = (String) -> Byte?
/** Method to convert a [Byte] to its [String] representation. */
public typealias ByteToString = (Byte) -> String

public val defaultStringToByte: StringToByte = String::toByteOrNull
public val defaultByteToString: ByteToString = Byte::toString

internal class ByteValueDelegate(
    override val name: String,
    private val toByte: StringToByte = defaultStringToByte,
    private val toString: ByteToString = defaultByteToString
) : NamedValueProperty<List<Byte>?> {
    override fun getValue(thisRef: StringValuesBuilder, property: KProperty<*>): List<Byte>? =
        thisRef.getAll(name)?.mapNotNull(toByte)

    override fun setValue(thisRef: StringValuesBuilder, property: KProperty<*>, value: List<Byte>?) {
        if (value == null) {
            thisRef -= name
        } else {
            thisRef[name] = value.map(toString)
        }
    }
}

internal class ByteDelegateProvider(
    private val name: String?,
    private val toByte: StringToByte = defaultStringToByte,
    private val toString: ByteToString = defaultByteToString
) : NamedValuePropertyProvider<List<Byte>?> {
    override fun provideDelegate(
        thisRef: StringValuesBuilder,
        property: KProperty<*>
    ): NamedValueProperty<List<Byte>?> {
        return ByteValueDelegate(name ?: property.name, toByte, toString)
    }
}
