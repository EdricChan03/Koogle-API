package io.github.edricchan03.koogle_api.plugin.generator

import org.gradle.api.Action
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.workers.WorkAction
import org.gradle.workers.WorkParameters
import org.gradle.workers.WorkQueue
import kotlin.reflect.KMutableProperty0

/**
 * Sets the receiver property only if the [other] provider [has a value][Provider.isPresent].
 */
internal fun <T : Any> Property<T>.setIfPresent(other: Provider<out T>) {
    if (other.isPresent) set(other)
}

/**
 * Sets the receiver mutable Kotlin property only if the [other] provider
 * [has a value][Provider.isPresent].
 */
internal fun <T : Any> KMutableProperty0<T>.setIfPresent(other: Provider<out T>) {
    if (other.isPresent) set(other.get())
}

/**
 * Submits a piece of work to be executed asynchronously.
 *
 * Execution of the work may begin immediately.
 *
 * Work submitted using [org.gradle.workers.WorkerExecutor.processIsolation] will execute
 * in an idle daemon that meets the requirements set in the
 * [org.gradle.workers.ProcessWorkerSpec].
 * If no idle daemons are available, a new daemon will be started.
 * Any errors will be thrown from [WorkQueue.await] or from the surrounding task action
 * if [WorkQueue.await] is not used.
 */
internal inline fun <reified C : WorkAction<T>, T : WorkParameters> WorkQueue.submit(
    paramsAction: Action<in T>
) = submit(C::class.java, paramsAction)
