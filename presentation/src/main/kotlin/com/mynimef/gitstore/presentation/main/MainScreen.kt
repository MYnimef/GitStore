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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                    Navigation.Featured.route,
                    Navigation.AppSearch.route,
                    Navigation.Settings.route -> true
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
            startDestination = Navigation.Featured.route,
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
        "Featured" to Navigation.Featured,
        "Search" to Navigation.AppSearch,
        "Settings" to Navigation.Settings
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

    composable(route = Navigation.Featured.route) {
        {}
    }

    composable(route = Navigation.AppSearch.route) {
        AppSearchScreen()
    }

    composable(route = Navigation.Settings.route) {
        SettingsScreen()
    }

    composable(route = Navigation.Integrations.route) {
        IntegrationsScreen()
    }

    composable(
        route = Navigation.AppDetails.route,
        arguments = listOf(
            navArgument("source") { type = NavType.StringType },
            navArgument("userId") { type = NavType.StringType },
            navArgument("repoId") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        val source = backStackEntry.arguments?.getString("source") ?: ""
        val userId = backStackEntry.arguments?.getString("userId") ?: ""
        val repoId = backStackEntry.arguments?.getString("repoId") ?: ""

        AppDetailsScreen()
    }

}