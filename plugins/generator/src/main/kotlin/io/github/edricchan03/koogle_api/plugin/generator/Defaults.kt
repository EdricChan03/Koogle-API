package io.github.edricchan03.koogle_api.plugin.generator

import io.github.edricchan03.koogle_api.plugin.generator.tasks.SchemaFileNameMapper
import org.gradle.api.Task
import org.gradle.api.file.Directory
import org.gradle.api.file.RegularFile
import org.gradle.api.provider.Provider

public object Defaults {
    /** @see KoogleApiGeneratorPluginExtension.discoveryDocsOutputDir */
    public val Task.defaultOutputDiscoveryDocsDir: Provider<Directory> get() = project.layout.buildDirectory.dir("koogle-api-disc-docs")

    /** @see KoogleApiGeneratorPluginExtension.discoveryUrl */
    public const val defaultRootDiscoveryUrl: String = "https://www.googleapis.com/discovery/v1/apis"

    /** @see KoogleApiGeneratorPluginExtension.rootDiscoveryDocOutputFile */
    public val Task.defaultRootDiscoveryDoc: Provider<RegularFile> get() = defaultOutputDiscoveryDocsDir.map { it.file("index.json") }

    /** @see KoogleApiGeneratorPluginExtension.outputFileNameMapper */
    public val defaultOutputFileNameMapper: SchemaFileNameMapper =
        SchemaFileNameMapper { "${it.id.replace(":", "-")}.json" }
}
