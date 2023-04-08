package io.github.edricchan03.koogle_api.plugin.generator

import io.github.edricchan03.koogle_api.plugin.generator.tasks.SchemaFileNameMapper
import org.gradle.api.Task

object Defaults {
    /** @see KoogleApiGeneratorPluginExtension.discoveryDocsOutputDir */
    val Task.defaultOutputDiscoveryDocsDir get() = project.layout.buildDirectory.dir("koogle-api-disc-docs")

    /** @see KoogleApiGeneratorPluginExtension.discoveryUrl */
    const val defaultRootDiscoveryUrl = "https://www.googleapis.com/discovery/v1/apis"

    /** @see KoogleApiGeneratorPluginExtension.rootDiscoveryDocOutputFile */
    val Task.defaultRootDiscoveryDoc get() = defaultOutputDiscoveryDocsDir.map { it.file("index.json") }

    /** @see KoogleApiGeneratorPluginExtension.outputFileNameMapper */
    val Task.defaultOutputFileNameMapper get() = SchemaFileNameMapper { "${it.id.replace(":", "-")}.json" }
}
