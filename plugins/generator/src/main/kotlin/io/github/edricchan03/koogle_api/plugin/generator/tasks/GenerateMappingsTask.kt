package io.github.edricchan03.koogle_api.plugin.generator.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

/** Task that generates Google API mappings. */
abstract class GenerateMappingsTask : DefaultTask() {
    /** The output directory to output the generated classes to. */
    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    /** The discovery API URL to use. */
    @get:Input
    abstract val discoveryUrl: Property<String>

    @TaskAction
    fun generateMappings() {

    }
}
