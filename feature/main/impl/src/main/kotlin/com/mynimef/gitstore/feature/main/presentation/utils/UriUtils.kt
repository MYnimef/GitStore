package com.mynimef.gitstore.feature.main.presentation.utils

import android.net.Uri
import com.mynimef.gitstore.domain.models.Deeplink

/**
 * Converts an [Uri] into a [Deeplink] data object.
 *
 * Extracts the host, path, and query parameters from the URI.
 * The host and path may be `null` if not present in the URI.
 * Query parameters are parsed into a map, with parameter names as keys
 * and their corresponding values (or empty strings if `null`) as values.
 *
 * @receiver The [Uri] to be converted.
 * @return A [Deeplink] instance containing the parsed data.
 */
fun Uri.toDeeplink(): Deeplink {
    val host = this.host
    val path = this.path
    val params = this.queryParameterNames.associateWith { this.getQueryParameter(it).orEmpty() }

    return Deeplink(
        host = host,
        path = path,
        params = params
    )
}