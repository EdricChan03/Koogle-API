package io.github.edricchan03.koogle_api.plugin.generator

import io.github.edricchan03.koogle_api.plugin.generator.tasks.DownloadDiscoveriesTask
import io.github.edricchan03.koogle_api.plugin.generator.tasks.DownloadRootDiscoveryTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.registering

/** Plugin that auto-generates the Google HTTP API mappings. */
public class KoogleApiGeneratorPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        // Register extension
        val extension = project.extensions.create<KoogleApiGeneratorPluginExtension>("koogleApiGenerator")

        // Register tasks
        project.tasks.apply {
            val downloadRootDiscovery by registering(DownloadRootDiscoveryTask::class) {
                description = "Downloads the root Google API discovery document and saves it to disk"
                group = PLUGIN_TASK_GROUP
                discoveryUrl.setIfPresent(extension.discoveryUrl)
                outputFile.setIfPresent(extension.rootDiscoveryDocOutputFile)
            }

            val downloadDiscoveries by registering(DownloadDiscoveriesTask::class) {
                dependsOn(downloadRootDiscovery)
                description = "Downloads all Google API discovery documents from the root document " +
                    "and saves them to disk"
                group = PLUGIN_TASK_GROUP
                rootDiscoveryDocFile.setIfPresent(extension.rootDiscoveryDocOutputFile)
                outputDir.setIfPresent(extension.discoveryDocsOutputDir)
                ::mapper.setIfPresent(extension.outputFileNameMapper)
            }
        }

    public companion object {
        public const val PLUGIN_TASK_GROUP: String = "koogleApi"
    }
}
