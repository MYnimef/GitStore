package com.mynimef.gitstore.presentation.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

internal enum class EColorTheme {

    DARK,

    LIGHT,

}

/**
 *
 */
@Composable
internal fun GitStorePreviewTheme(
    theme: EColorTheme,
    content: @Composable () -> Unit
) = GitStoreTheme(
    darkTheme = when (theme) {
        EColorTheme.DARK -> true
        EColorTheme.LIGHT -> false
    }
) {
    Scaffold {
        Box(modifier = Modifier.fillMaxSize().padding(it)) {
            content()
        }
    }
}