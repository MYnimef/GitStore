package com.mynimef.gitstore.presentation.di

import com.mynimef.gitstore.domain.EventListener
import com.mynimef.gitstore.domain.models.Event
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class EventFlowAdapter @Inject constructor() : EventListener {

    private val _events = MutableSharedFlow<Event>()
    val events = _events.asSharedFlow()

    override suspend fun onEvent(event: Event) {
        _events.emit(event)
    }

}