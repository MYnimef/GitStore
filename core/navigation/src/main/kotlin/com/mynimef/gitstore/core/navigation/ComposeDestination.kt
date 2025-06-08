package com.mynimef.gitstore.core.navigation

import androidx.navigation.NavGraphBuilder

/**
 *
 */
interface ComposeDestination {

    /**
     *
     */
    val route: String

    /**
     *
     */
    fun register(builder: NavGraphBuilder)

}