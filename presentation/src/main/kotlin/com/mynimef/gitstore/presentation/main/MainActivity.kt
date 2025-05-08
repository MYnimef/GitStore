package com.mynimef.gitstore.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.mynimef.gitstore.domain.DeeplinkHandler
import com.mynimef.gitstore.presentation.theme.GitStoreTheme
import com.mynimef.gitstore.presentation.utils.toDeeplink
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {

    @Inject lateinit var deeplinkHandler: DeeplinkHandler

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent.data?.let { uri ->
            lifecycleScope.launch {
                deeplinkHandler.handleDeeplink(deeplink = uri.toDeeplink())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GitStoreTheme {
                MainScreen()
            }
        }
    }

}