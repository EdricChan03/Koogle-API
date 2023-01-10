package io.github.edricchan03.koogle_api.plugin.generator

import io.github.edricchan03.koogle_api.plugin.generator.data.DirectoryItem
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.registering
import java.net.URL

abstract class KoogleApiGeneratorPluginExtension {
    /** The discovery URL to use. */
    abstract val discoveryUrl: Property<URL>

    /** The discovery URL to use. */
    fun discoveryUrl(url: String) {
        discoveryUrl.set(URL(url))
    }

    /**
     * The file to output to.
     * If this value is set to `null`, it will be output to the project's build directory
     * with the file name "koogle-api-discovery.json".
     */
    abstract val rootDiscoveryDocOutputFile: RegularFileProperty

    /**
     * The directory to save all discovery documents to.
     * If this value is set to `null`, it will by default be saved to the project's build directory
     * in a sub-directory "koogle-api-disc-docs".
     */
    abstract val discoveryDocsOutputDir: DirectoryProperty

    /** Mapping function used to generate the output file name based from the specified [DirectoryItem]. */
    abstract val outputFileNameMapper: Property<SchemaFileNameMapper?>

    /** Mapping function used to generate the output file name based from the specified [DirectoryItem]. */
    fun outputFileNameMapper(mapper: SchemaFileNameMapper) = outputFileNameMapper.set(mapper)

}

/** Plugin that auto-generates the Google HTTP API mappings. */
class KoogleApiGeneratorPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        // Register extension
        val extension = project.extensions.create<KoogleApiGeneratorPluginExtension>("koogleApiGenerator")

        // Register tasks
        project.tasks.apply {
            val downloadRootDiscovery by registering(DownloadRootDiscoveryTask::class) {
                description = "Downloads the root Google API discovery document and saves it to disk"
                group = "koogleApi"
                discoveryUrl.set(extension.discoveryUrl)
                outputFile.set(extension.rootDiscoveryDocOutputFile)
            }
            val downloadDiscoveries by registering(DownloadDiscoveriesTask::class) {
                dependsOn(downloadRootDiscovery)
                description = "Downloads all Google API discovery documents from the root document " +
                    "and saves them to disk"
                group = "koogleApi"
                rootDiscoveryDocFile.set(extension.rootDiscoveryDocOutputFile)
                outputDir.set(extension.discoveryDocsOutputDir)
                outputFileNameMapper.set(extension.outputFileNameMapper)
            }
        }
    }
}
