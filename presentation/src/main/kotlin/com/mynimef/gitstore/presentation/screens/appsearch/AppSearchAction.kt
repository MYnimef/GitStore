package com.mynimef.gitstore.presentation.screens.appsearch

sealed interface AppSearchAction {

    data class OnSearch(val query: String) : AppSearchAction

    data object OnDownloadApp : AppSearchAction

}