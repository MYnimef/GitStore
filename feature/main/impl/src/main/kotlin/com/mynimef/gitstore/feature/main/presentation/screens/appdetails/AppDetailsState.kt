package com.mynimef.gitstore.feature.main.presentation.screens.appdetails

sealed interface AppDetailsState {

    data class Success(
        val iconUrl: String?,
        val name: String,
        val developer: String,
        val details: String
    ): AppDetailsState

    data object Loading: AppDetailsState

    data object Error: AppDetailsState

}