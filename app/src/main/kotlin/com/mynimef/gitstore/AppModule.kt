package com.mynimef.gitstore

import android.content.Context
import com.mynimef.gitstore.data.local.AppStorageImpl
import com.mynimef.gitstore.data.remote.AppNetworkImpl
import com.mynimef.gitstore.domain.AppStorage
import com.mynimef.gitstore.domain.DeeplinkHandler
import com.mynimef.gitstore.domain.AppNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class AppModule {

    @Singleton
    @Provides
    fun provideAppStorage(
        @ApplicationContext context: Context
    ): AppStorage {
        return AppStorageImpl(context = context)
    }

    @Singleton
    @Provides
    fun provideNetwork(): AppNetwork {
        return AppNetworkImpl()
    }

    @Singleton
    @Provides
    fun provideDeeplinkHandler(
        storage: AppStorage
    ): DeeplinkHandler {
        return DeeplinkHandler(storage = storage)
    }

}