package io.github.edricchan03.koogle_api.plugin.generator.tasks

import io.github.edricchan03.koogle_api.plugin.generator.Defaults
import io.github.edricchan03.koogle_api.plugin.generator.Defaults.defaultRootDiscoveryDoc
import io.github.edricchan03.koogle_api.plugin.generator.http.createClient
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.net.URL

/** Task that downloads and saves the root Google Discovery document to disk. */
public abstract class DownloadRootDiscoveryTask : DefaultTask() {
    /** URL to the root discovery document. */
    @get:Input
    @get:Optional
    public abstract val discoveryUrl: Property<URL>

    /** Output file where the discovery document will be saved. */
    @get:OutputFile
    @get:Optional
    public abstract val outputFile: RegularFileProperty

    init {
        discoveryUrl.convention(URL(Defaults.defaultRootDiscoveryUrl))
        outputFile.convention(defaultRootDiscoveryDoc)
    }

    @TaskAction
    public fun downloadRootDiscovery() {
        val url = discoveryUrl.get()
        val file = outputFile.get().asFile
        println("Downloading root Google discovery document from $url...")
        createClient().use {
            // TODO: Is this runBlocking needed?
            runBlocking {
                println("Writing to $file...")
                file.writeText(it.get(url).bodyAsText())
            }
        }
        println("Done writing.")
    }
}
