package io.github.edricchan03.koogle_api.plugin.generator

/**
 * APIs annotated with this annotation should not be used for public use, and no
 * backwards-compat guarantees are provided
 */
@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "This is an internal API which should not be used for public use. " +
        "APIs annotated with this opt-in may also change at any point with no " +
        "guarantees in backwards-compatibility."
)
@MustBeDocumented
public annotation class InternalPluginApi
