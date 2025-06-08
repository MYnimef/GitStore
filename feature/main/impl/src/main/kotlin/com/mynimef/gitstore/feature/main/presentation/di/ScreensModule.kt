package com.mynimef.gitstore.feature.main.presentation.di

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mynimef.gitstore.core.navigation.impl.ComposeDestination
import com.mynimef.gitstore.feature.main.api.IntegrationDestination
import com.mynimef.gitstore.feature.main.api.MainDestination
import com.mynimef.gitstore.feature.main.presentation.screens.integrations.IntegrationsScreen
import com.mynimef.gitstore.feature.main.presentation.screens.main.MainScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
internal class ScreensModule {

    @Provides
    @IntoSet
    fun provideMainScreen() = object : ComposeDestination {
        override val route = MainDestination.ROUTE_KEY

        override fun register(builder: NavGraphBuilder) {
            builder.composable(route = route) {
                MainScreen()
            }
        }
    }

    @Provides
    @IntoSet
    fun provideIntegrationsScreen() = object : ComposeDestination {
        override val route = IntegrationDestination.ROUTE_KEY

        override fun register(builder: NavGraphBuilder) {
            builder.composable(route = route) {
                IntegrationsScreen()
            }
        }
    }

}