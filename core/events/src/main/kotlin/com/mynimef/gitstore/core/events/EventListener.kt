package com.mynimef.gitstore.core.events

/**
 *
 */
interface EventListener {

    suspend fun onEvent(event: Event)

}