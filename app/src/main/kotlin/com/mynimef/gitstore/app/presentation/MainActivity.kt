package com.mynimef.gitstore.app.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.mynimef.gitstore.common.uikit.theme.GitStoreTheme
import com.mynimef.gitstore.domain.DeeplinkHandler
import com.mynimef.gitstore.feature.main.presentation.utils.toDeeplink
import com.mynimef.navigation.ComposeDestination
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Main activity of the GitStore application.
 * Handles deep links and serves as the entry point for the app's UI.
 *
 * This activity is responsible for:
 * - Managing deep link navigation
 * - Setting up the main UI using Jetpack Compose
 * - Initializing the app's theme
 */
@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {

    @Inject
    @JvmSuppressWildcards
    lateinit var deeplinkHandlersMap: Map<String, DeeplinkHandler>

    @Inject
    @JvmSuppressWildcards
    lateinit var destinationsSet: Set<ComposeDestination>

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent.data?.let { uri ->
            lifecycleScope.launch {
                deeplinkHandlersMap["${uri.host}${uri.path}"]?.handleDeeplink(deeplink = uri.toDeeplink())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GitStoreTheme {
                MainScreen(
                    destinations = destinationsSet
                )
            }
        }
    }

}