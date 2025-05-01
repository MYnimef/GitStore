package com.mynimef.gitstore.presentation.appdetails

sealed interface AppDetailsAction {

    data object OnDownloadClick: AppDetailsAction

}