package com.mynimef.gitstore.di

import android.content.Context
import com.mynimef.gitstore.data.local.AppStorageImpl
import com.mynimef.gitstore.data.remote.AppNetworkImpl
import com.mynimef.gitstore.domain.AppNetwork
import com.mynimef.gitstore.domain.AppStorage
import com.mynimef.gitstore.domain.DeeplinkHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt module that provides application-wide dependencies.
 * This module is installed in the [dagger.hilt.components.SingletonComponent] to ensure dependencies are scoped to the application lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
internal class AppModule {

    /**
     * Provides a singleton instance of [com.mynimef.gitstore.domain.AppStorage] for managing local data persistence.
     *
     * @param context The application context
     * @return An implementation of [com.mynimef.gitstore.domain.AppStorage]
     */
    @Singleton
    @Provides
    fun provideAppStorage(
        @ApplicationContext context: Context
    ): AppStorage {
        return AppStorageImpl(context = context)
    }

    /**
     * Provides a singleton instance of [com.mynimef.gitstore.domain.AppNetwork] for handling network operations.
     *
     * @return An implementation of [com.mynimef.gitstore.domain.AppNetwork]
     */
    @Singleton
    @Provides
    fun provideNetwork(): AppNetwork {
        return AppNetworkImpl()
    }

    /**
     * Provides a singleton instance of [com.mynimef.gitstore.domain.DeeplinkHandler] for managing deep link navigation.
     *
     * @param storage The [AppStorage] instance used by the deeplink handler
     * @return A [com.mynimef.gitstore.domain.DeeplinkHandler] instance
     */
    @Singleton
    @Provides
    fun provideDeeplinkHandler(
        storage: AppStorage
    ): DeeplinkHandler {
        return DeeplinkHandler(storage = storage)
    }

}