package com.mynimef.gitstore.presentation.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Enum representing the available color themes for the application.
 * Used to control the appearance of previews in the IDE.
 */
internal enum class EColorTheme {
    /** Dark theme variant */
    DARK,

    /** Light theme variant */
    LIGHT,
}

/**
 * A preview theme composable that wraps content with the application's theme and scaffold.
 * This is primarily used for previewing UI components in the IDE.
 *
 * @param theme The color theme to apply (dark or light)
 * @param content The composable content to be displayed within the theme
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