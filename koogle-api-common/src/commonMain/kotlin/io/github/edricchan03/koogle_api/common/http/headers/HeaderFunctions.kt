package io.github.edricchan03.koogle_api.common.http.headers

import io.github.edricchan03.koogle_api.common.http.headers.delegate.ContentTypeDelegateProvider
import io.github.edricchan03.koogle_api.common.http.values.delegate.base.NamedValuePropertyProvider
import io.github.edricchan03.koogle_api.common.http.values.delegate.single.ValueSingleDelegateProvider
import io.github.edricchan03.koogle_api.common.http.values.delegate.single.single
import io.ktor.http.*
import io.ktor.util.*

/**
 * Creates an optional `Content-Type` header of type `List<ContentType>` with the given
 * [name] in this [KoogleHeadersBuilder] instance.
 *
 * If no [name] is provided, the property's name will be used.
 */
public fun StringValuesBuilder.contentTypeValues(
    name: String? = null
): NamedValuePropertyProvider<List<ContentType>?> = ContentTypeDelegateProvider(name)

/**
 * Creates an optional `Content-Type` header of type [ContentType] with the given
 * [name] in this [KoogleHeadersBuilder] instance.
 *
 * If no [name] is provided, the property's name will be used.
 */
public fun StringValuesBuilder.contentTypeValue(
    name: String? = null
): ValueSingleDelegateProvider<ContentType?> = contentTypeValues(name).single()
