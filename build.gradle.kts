plugins {
    alias(libs.plugins.kotlin.serialization) apply false
    dev.adamko.`dokkatoo-html`
}

dependencies {
    dokkatoo("io.github.edricchan03.koogle-api:generator")
    dokkatoo(projects.koogleApiCommon)

    // TODO: Remove when https://github.com/adamko-dev/dokkatoo/issues/14 is fixed
    dokkatooPluginHtml(
        dokkatoo.versions.jetbrainsDokka.map { dokkaVersion ->
            "org.jetbrains.dokka:all-modules-page-plugin:$dokkaVersion"
        }
    )
}
