package com.mynimef.gitstore.domain.models

/**
 * Represents a parsed deep link from a URI.
 *
 * @property host The host component of the URI (e.g., "example.com"), or `null` if absent.
 * @property path The path component of the URI (e.g., "/some/path"), or `null` if absent.
 * @property params A map of query parameters from the URI, where each key-value pair represents a parameter and its value.
 */
data class Deeplink(
    val host: String?,
    val path: String?,
    val params: Map<String, String>
)
