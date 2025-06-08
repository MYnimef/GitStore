package com.mynimef.gitstore.feature.main.presentation.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mynimef.gitstore.domain.models.Navigation
import com.mynimef.gitstore.feature.main.presentation.screens.appsearch.AppSearchScreen
import com.mynimef.gitstore.feature.main.presentation.screens.appsettings.SettingsScreen

@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: MainViewModel = hiltViewModel()
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "",
                onClickNavItem = {
                    navController.navigate(it.route)
                }
            )
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
    val navItems = remember { listOf(
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

}