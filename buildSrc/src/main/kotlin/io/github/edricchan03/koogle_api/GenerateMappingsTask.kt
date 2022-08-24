package io.github.edricchan03.koogle_api

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

/** Task that generates Google API mappings. */
abstract class GenerateMappingsTask : DefaultTask() {
    /** The output directory to output the generated classes to. */
    @get:OutputDirectory
    abstract val outputDir: File
    /** The discovery API URL to use. */
    @get:Input
    abstract val discoveryUrl: String

    @TaskAction
    fun generateMappings() {

    }
}
