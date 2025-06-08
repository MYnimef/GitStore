package com.mynimef.gitstore.feature.main.presentation.screens.settings

sealed interface SettingsAction {

    data object OnIntegrationsClick : SettingsAction

}