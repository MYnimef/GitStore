package com.mynimef.gitstore.domain.models

sealed interface Event {

    data class OpenUrl(val url: String) : Event

    data class NavigateTo(val navigation: Navigation) : Event

    data object NavigateBack : Event

}