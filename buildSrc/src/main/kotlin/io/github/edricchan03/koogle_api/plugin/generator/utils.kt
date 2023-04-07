package io.github.edricchan03.koogle_api.plugin.generator

import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider

/**
 * Sets the receiver property only if the [other] provider [has a value][Provider.isPresent].
 */
fun <T> Property<T>.setIfPresent(other: Provider<out T?>) {
    if (other.isPresent) set(other)
}
