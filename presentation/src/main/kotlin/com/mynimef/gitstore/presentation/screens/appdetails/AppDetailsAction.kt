package com.mynimef.gitstore.presentation.screens.appdetails

sealed interface AppDetailsAction {

    data object OnDownloadClick: AppDetailsAction

}