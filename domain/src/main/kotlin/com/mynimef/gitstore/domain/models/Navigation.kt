package com.mynimef.gitstore.domain.models

/**
 *
 */
sealed class Navigation(val route: String) {

    object Featured: Navigation(route = "featured")

    object AppSearch: Navigation(route = "app_search")

    object Settings: Navigation(route = "settings")

    object Integrations: Navigation(route = "integrations")

    object AppDetails: Navigation(route = "app_details/{source}/{userId}/{repoId}") {
        fun createRoute(
            source: String,
            userId: String,
            repoId: String
        ): String = "app_details/$source/$userId/$repoId"
    }

}