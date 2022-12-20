package io.github.edricchan03.koogle_api.common.http.values.delegate.base

import io.ktor.util.*
import kotlin.properties.PropertyDelegateProvider

/** A [PropertyDelegateProvider] that provides [NamedValueProperty] instances. */
public interface NamedValuePropertyProvider<T> : PropertyDelegateProvider<StringValuesBuilder, NamedValueProperty<T>>
