package com.mynimef.gitstore.di

import com.mynimef.gitstore.domain.DeeplinkHandler
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.Multibinds

@Module
@InstallIn(SingletonComponent::class)
internal interface DeeplinkHandlersModule {

    @get:Multibinds
    val deeplinkHandlerMap: Map<String, DeeplinkHandler>

}