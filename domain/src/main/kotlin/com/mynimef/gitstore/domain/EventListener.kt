package com.mynimef.gitstore.domain

import com.mynimef.gitstore.domain.models.Event

interface EventListener {

    suspend fun onEvent(event: Event)
    
}