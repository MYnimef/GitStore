package com.mynimef.gitstore.app.presentation

import androidx.browser.customtabs.CustomTabsIntent.Builder
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mynimef.gitstore.core.events.Event
import com.mynimef.gitstore.core.navigation.impl.ComposeDestination
import com.mynimef.gitstore.feature.main.api.MainDestination

@Composable
internal fun MainScreen(
    destinations: Collection<ComposeDestination>
) {
    val viewModel: MainViewModel = hiltViewModel()
    val navController = rememberNavController()

    val currentContext by rememberUpdatedState(LocalContext.current)

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {

                is Event.OpenUrl -> {
                    val uri = event.url.toUri()
                    val customTabsIntent = Builder()
                        .setShowTitle(true)
                        .build()
                    customTabsIntent.launchUrl(currentContext, uri)
                }

                is Event.NavigateTo -> {
                    navController.navigate(event.navigation.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }

                Event.NavigateBack -> {
                    navController.popBackStack()
                }

            }
        }
    }

    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = MainDestination.ROUTE_KEY,
        builder = {
            destinations.forEach { destination ->
                destination.register(builder = this)
            }
            composable(route = INITIAL_ROUTE) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "add destinations through DI",
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    )
}

private const val INITIAL_ROUTE = "initial_route"