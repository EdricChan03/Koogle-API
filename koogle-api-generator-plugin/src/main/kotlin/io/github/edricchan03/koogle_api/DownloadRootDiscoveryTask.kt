package io.github.edricchan03.koogle_api

import io.github.edricchan03.koogle_api.Defaults.defaultRootDiscoveryDoc
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.net.URL

/** Task that downloads and saves the root Google Discovery document to disk. */
abstract class DownloadRootDiscoveryTask : DefaultTask() {
    /** URL to the root discovery document. */
    @get:Input
    @get:Optional
    var discoveryUrl: URL = URL("https://www.googleapis.com/discovery/v1/apis")

    /** Output file where the discovery document will be saved. */
    @get:OutputFile
    @get:Optional
    var outputFile: File = defaultRootDiscoveryDoc

    @TaskAction
    fun downloadRootDiscovery() {
        println("Downloading root Google discovery document from $discoveryUrl...")
        val client = HttpClient(CIO)
        // TODO
        runBlocking {
            println("Writing to $outputFile...")
            outputFile.writeText(client.get(discoveryUrl).bodyAsText())
        }
        println("Done writing.")
    }
}
