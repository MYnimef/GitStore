package com.mynimef.gitstore.presentation.main

import androidx.browser.customtabs.CustomTabsIntent.Builder
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mynimef.gitstore.domain.models.Event
import com.mynimef.gitstore.domain.models.Navigation
import com.mynimef.gitstore.presentation.screens.appdetails.AppDetailsScreen
import com.mynimef.gitstore.presentation.screens.appsearch.AppSearchScreen
import com.mynimef.gitstore.presentation.screens.integrations.IntegrationsScreen
import com.mynimef.gitstore.presentation.screens.settings.SettingsScreen

@Composable
internal fun MainScreen() {
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

    Scaffold(
        bottomBar = {
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val isVisible by remember { derivedStateOf {
                when (navBackStackEntry.value?.destination?.route) {
                    Navigation.FEATURED.route,
                    Navigation.APP_SEARCH.route,
                    Navigation.SETTINGS.route -> true
                    else -> false
                }
            } }
            if (isVisible) {
                BottomNavigationBar(
                    currentRoute = "",
                    onClickNavItem = viewModel::navigate
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
            ,
            navController = navController,
            startDestination = Navigation.FEATURED.route,
            builder = NavGraphBuilder::mainNavigation
        )
    }
}

@Composable
private fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    currentRoute: String?,
    onClickNavItem: (Navigation) -> Unit,
) = NavigationBar(
    modifier = modifier
) {
    val navItems = rememberSaveable { listOf(
        "Featured" to Navigation.FEATURED,
        "Search" to Navigation.APP_SEARCH,
        "Settings" to Navigation.SETTINGS
    ) }
    navItems.forEach { item ->
        NavigationBarItem(
            icon = {  },
            label = { Text(item.first) },
            selected = true,
            onClick = { onClickNavItem(item.second) }
        )
    }
}

private fun NavGraphBuilder.mainNavigation() {
    composable(route = Navigation.FEATURED.route) {
        {}
    }
    composable(route = Navigation.APP_SEARCH.route) {
        AppSearchScreen()
    }
    composable(route = Navigation.SETTINGS.route) {
        SettingsScreen()
    }
    composable(route = Navigation.INTEGRATIONS.route) {
        IntegrationsScreen()
    }
    composable(route = Navigation.APP_DETAILS.route) {
        AppDetailsScreen()
    }
}