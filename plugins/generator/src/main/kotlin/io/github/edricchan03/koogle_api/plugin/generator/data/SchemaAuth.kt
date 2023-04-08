package io.github.edricchan03.koogle_api.plugin.generator.data

import kotlinx.serialization.Serializable

// Auth-related data classes

/** Data class to represent [RestDescription.auth]. */
@Serializable
public data class SchemaAuth(
    /** OAuth 2.0 authentication information. */
    val oauth2: OAuth2? = null
) {
    /** Data class to represent additional metadata of OAuth 2.0 auth in [SchemaAuth.oauth2]. */
    @Serializable
    public data class OAuth2(
        /** Available OAuth 2.0 scopes. */
        val scopes: Map<String, Scope>? = emptyMap()
    ) {
        /** Maps each scope to a pair of names and their descriptions. */
        val scopesAsPair: List<Pair<String, String?>>? = scopes?.map { (key, value) -> key to value.description }

        /** Data class to represent additional metadata of a scope in [OAuth2.scopes]. */
        @Serializable
        public data class Scope(
            /** Description of scope. */
            val description: String? = null
        )
    }

}
