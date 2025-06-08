package com.mynimef.gitstore.feature.main.presentation.screens.appsettings

sealed interface SettingsAction {

    data object OnIntegrationsClick : SettingsAction

}