package com.mynimef.gitstore.core.events.lib

interface EventListener {

    suspend fun onEvent(event: Event)

}