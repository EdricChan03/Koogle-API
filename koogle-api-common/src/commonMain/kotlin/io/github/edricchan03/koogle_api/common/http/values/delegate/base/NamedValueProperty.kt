package io.github.edricchan03.koogle_api.common.http.values.delegate.base

import io.ktor.util.*
import kotlin.properties.ReadWriteProperty

/**
 * A [StringValuesBuilder] property delegate that holds the [name] to be used
 * for storing the value.
 */
public interface NamedValueProperty<T> : ReadWriteProperty<StringValuesBuilder, T> {
    public val name: String
}
