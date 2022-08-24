package io.github.edricchan03.koogle_api.plugin.generator

import io.github.edricchan03.koogle_api.plugin.generator.data.DirectoryItem
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.registering
import java.io.File
import java.net.URL

abstract class KoogleApiGeneratorPluginExtension {
    /** The discovery URL to use. */
    var discoveryUrl: URL? = URL("https://www.googleapis.com/discovery/v1/apis")

    /** The discovery URL to use. */
    fun discoveryUrl(url: String) {
        discoveryUrl = URL(url)
    }

    /**
     * The file to output to.
     * If this value is set to `null`, it will be output to the project's build directory
     * with the file name "koogle-api-discovery.json".
     */
    var rootDiscoveryDocOutputFile: File? = null

    /**
     * The directory to save all discovery documents to.
     * If this value is set to `null`, it will by default be saved to the project's build directory
     * in a sub-directory "koogle-api-disc-docs".
     */
    var discoveryDocsOutputDir: File? = null

    /** Mapping function used to generate the output file name based from the specified [DirectoryItem]. */
    var outputFileNameMapper: SchemaFileNameMapper? = null
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
                extension.discoveryUrl?.let { discoveryUrl = it }
                extension.rootDiscoveryDocOutputFile?.let { outputFile = it }
            }
            val downloadDiscoveries by registering(DownloadDiscoveriesTask::class) {
                dependsOn(downloadRootDiscovery)
                description = "Downloads all Google API discovery documents from the root document " +
                    "and saves them to disk"
                group = "koogleApi"
                extension.rootDiscoveryDocOutputFile?.let { rootDiscoveryDocFile = it }
                extension.discoveryDocsOutputDir?.let { outputDir = it }
                extension.outputFileNameMapper?.let { outputFileNameMapper = it }
            }
        }
    }
}
