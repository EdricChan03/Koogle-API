package io.github.edricchan03.koogle_api.plugin.generator.work

import io.github.edricchan03.koogle_api.plugin.generator.data.json.sortKeys
import io.github.edricchan03.koogle_api.plugin.generator.http.createClient
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream
import kotlinx.serialization.json.jsonObject
import org.gradle.workers.WorkAction

/**
 * Work action to download a discovery given the [work parameters][DownloadWorkParams].
 */
public abstract class DownloadDiscovery : WorkAction<DownloadWorkParams> {
    private val json = Json { prettyPrint = true }

    @OptIn(ExperimentalSerializationApi::class)
    override fun execute() {
        val (schemaUrl, schemaId, outputFile) = parameters
        println("Downloading ${schemaId}...")
        // TODO: Is this runBlocking needed?
        createClient().use {
            try {
                runBlocking {
//                    val data = it.get(parameters.schemaUrl).body<RestDescription>()
//                    Json.encodeToStream(data, parameters.outputFile.outputStream())
                    val bodyText = it.get(schemaUrl).bodyAsText()

                    // Sort the keys in alphabetical order
                    val bodyJson = json.parseToJsonElement(bodyText).jsonObject
                    json.encodeToStream(bodyJson.sortKeys(), outputFile.asFile.outputStream())

                    println("Done downloading $schemaId")
                }
            } catch (e: Exception) {
                println("Error downloading: $schemaUrl $e")
                e.printStackTrace()
                throw RuntimeException(e)
            }
        }
    }
}
