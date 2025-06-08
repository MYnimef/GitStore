package com.mynimef.gitstore.feature.main.presentation.screens.appdetails

sealed interface AppDetailsAction {

    data object OnDownloadClick: AppDetailsAction

}