package com.mynimef.gitstore.presentation.di

import com.mynimef.gitstore.domain.EventListener
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class PresentationModule {

    @Binds
    abstract fun bindEventListener(
        adapter: EventFlowAdapter
    ): EventListener

}