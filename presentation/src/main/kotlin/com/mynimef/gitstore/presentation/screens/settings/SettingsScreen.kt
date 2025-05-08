package com.mynimef.gitstore.presentation.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mynimef.gitstore.presentation.theme.EColorTheme
import com.mynimef.gitstore.presentation.theme.GitStorePreviewTheme

@Composable
internal fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    SettingsScreen(
        onAction = viewModel::onAction
    )
}

@Composable
private fun SettingsScreen(
    onAction: (SettingsAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            onClick = {
                onAction(SettingsAction.OnIntegrationsClick)
            }
        ) {
            Text(text = "integrations")
        }
    }
}

@Composable
private fun SettingsScreenMockk() {
    SettingsScreen(
        onAction = {}
    )
}

@Preview
@Composable
private fun SettingsScreenDarkPreview() = GitStorePreviewTheme(EColorTheme.DARK) {
    SettingsScreenMockk()
}

@Preview
@Composable
private fun SettingsScreenLightPreview() = GitStorePreviewTheme(EColorTheme.LIGHT) {
    SettingsScreenMockk()
}