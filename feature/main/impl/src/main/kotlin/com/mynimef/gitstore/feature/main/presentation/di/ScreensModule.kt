package com.mynimef.gitstore.feature.main.presentation.di

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mynimef.gitstore.feature.main.presentation.screens.integrations.IntegrationsScreen
import com.mynimef.gitstore.feature.main.presentation.screens.main.MainScreen
import com.mynimef.gitstore.core.navigation.impl.ComposeDestination
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
        override val route = "main_screen"

        override fun register(builder: NavGraphBuilder) {
            builder.composable(route = route) {
                MainScreen()
            }
        }
    }

    @Provides
    @IntoSet
    fun provideIntegrationsScreen() = object : ComposeDestination {
        override val route = "integrations"

        override fun register(builder: NavGraphBuilder) {
            builder.composable(route = route) {
                IntegrationsScreen()
            }
        }
    }

}