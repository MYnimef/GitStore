package com.mynimef.gitstore.app.di

import com.mynimef.gitstore.core.navigation.ComposeDestination
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ElementsIntoSet

/**
 * Dagger Hilt module that provides application-wide dependencies.
 * This module is installed in the [SingletonComponent] to ensure dependencies are scoped to the application lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
internal class ComposeDestinationsModule {

    @Provides
    @ElementsIntoSet
    fun provideEmptyDestinationsSet(): Set<@JvmSuppressWildcards ComposeDestination> {
        return emptySet()
    }

}