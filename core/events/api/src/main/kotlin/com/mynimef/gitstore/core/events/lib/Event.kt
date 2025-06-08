package com.mynimef.gitstore.core.events.lib

import com.mynimef.gitstore.core.navigation.api.Destination

sealed interface Event {

    data class OpenUrl(val url: String) : Event

    data class NavigateTo(val navigation: Destination) : Event

    data object NavigateBack : Event

}