package com.mynimef.gitstore.di

import com.mynimef.gitstore.domain.IntegrationAuthHandler
import com.mynimef.gitstore.domain.models.Integration
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ElementsIntoSet

@Module
@InstallIn(SingletonComponent::class)
internal class IntegrationAuthHandlerModule {

    // fixes compilation when no handlers presented
    @Provides
    @ElementsIntoSet
    fun provideEmptyIntegrationsSet(): Set<@JvmSuppressWildcards Pair<Integration, IntegrationAuthHandler>> {
        return emptySet()
    }

    // converts set of pairs to map for better use
    @Provides
    fun provideIntegrationsMap(
        handlers: Set<@JvmSuppressWildcards Pair<Integration, IntegrationAuthHandler>>
    ): Map<Integration, IntegrationAuthHandler> {
        return handlers.toMap()
    }

}
