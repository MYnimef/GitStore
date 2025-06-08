package com.mynimef.gitstore.app.di

import com.mynimef.gitstore.core.events.EventListener
import com.mynimef.gitstore.app.EventFlowAdapter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Dagger Hilt module that provides presentation layer dependencies.
 * This module is installed in the [dagger.hilt.components.SingletonComponent] to ensure dependencies are scoped to the application lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
internal abstract class EventsModule {

    /**
     * Binds the [EventFlowAdapter] implementation to the [EventListener] interface.
     *
     * @param adapter The [EventFlowAdapter] implementation
     * @return The bound [EventListener] interface
     */
    @Binds
    abstract fun bindEventListener(
        adapter: EventFlowAdapter
    ): EventListener

}