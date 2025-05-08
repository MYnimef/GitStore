package com.mynimef.gitstore.presentation.di

import com.mynimef.gitstore.domain.EventListener
import com.mynimef.gitstore.domain.models.Event
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Adapter that converts domain events into Kotlin Flow streams.
 * This class implements the [EventListener] interface and provides a way to observe events
 * using Kotlin Flow in the presentation layer.
 */
@Singleton
internal class EventFlowAdapter @Inject constructor() : EventListener {

    /**
     * Internal mutable shared flow that emits events.
     * This is used to emit events from the domain layer to the presentation layer.
     */
    private val _events = MutableSharedFlow<Event>()

    /**
     * Public immutable shared flow that can be collected to observe events.
     * This is exposed to the presentation layer for subscribing to events.
     */
    val events = _events.asSharedFlow()

    /**
     * Handles incoming events from the domain layer by emitting them to the shared flow.
     *
     * @param event The event to be emitted
     */
    override suspend fun onEvent(event: Event) {
        _events.emit(event)
    }

}