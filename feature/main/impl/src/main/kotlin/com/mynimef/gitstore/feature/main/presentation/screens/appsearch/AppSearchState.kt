package com.mynimef.gitstore.feature.main.presentation.screens.appsearch

sealed interface AppSearchState {

    data class Success(
        val query: String,
        val apps: List<AppInfo>
    ): AppSearchState {

        data class AppInfo(
            val iconUrl: String?,
            val name: String,
            val developer: String
        )

    }

    data object Loading: AppSearchState

    data object Error: AppSearchState

}