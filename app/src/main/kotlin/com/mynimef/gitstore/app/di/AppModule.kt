package com.mynimef.gitstore.app.di

import android.content.Context
import com.mynimef.gitstore.data.local.AppStorageImpl
import com.mynimef.gitstore.data.remote.AppNetworkImpl
import com.mynimef.gitstore.domain.AppNetwork
import com.mynimef.gitstore.domain.AppStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt module that provides application-wide dependencies.
 * This module is installed in the [SingletonComponent] to ensure dependencies are scoped to the application lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
internal class AppModule {

    /**
     * Provides a singleton instance of [AppStorage] for managing local data persistence.
     *
     * @param context The application context
     * @return An implementation of [AppStorage]
     */
    @Singleton
    @Provides
    fun provideAppStorage(
        @ApplicationContext context: Context
    ): AppStorage {
        return AppStorageImpl(context = context)
    }

    /**
     * Provides a singleton instance of [AppNetwork] for handling network operations.
     *
     * @return An implementation of [AppNetwork]
     */
    @Singleton
    @Provides
    fun provideNetwork(): AppNetwork {
        return AppNetworkImpl()
    }

}