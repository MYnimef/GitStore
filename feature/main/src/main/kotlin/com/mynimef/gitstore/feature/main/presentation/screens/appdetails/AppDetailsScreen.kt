package com.mynimef.gitstore.feature.main.presentation.screens.appdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mynimef.gitstore.common.uikit.theme.EColorTheme
import com.mynimef.gitstore.common.uikit.theme.GitStorePreviewTheme

@Composable
internal fun AppDetailsScreen(
    viewModel: AppDetailsViewModel = hiltViewModel()
) {
    AppDetailsScreen(
        onAction = viewModel::onAction,
        state = viewModel.appDetailsStateFlow.collectAsState().value
    )
}

@Composable
private fun AppDetailsScreen(
    onAction: (AppDetailsAction) -> Unit,
    state: AppDetailsState
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        when (state) {
            AppDetailsState.Error -> {}
            AppDetailsState.Loading -> {}
            is AppDetailsState.Success -> SuccessScreen(
                state = state,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
    }
}

@Composable
private fun SuccessScreen(
    state: AppDetailsState.Success,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row {
            state.iconUrl?.let {

            }
            Column {
                Text(
                    text = state.name
                )
                Text(
                    text = state.developer
                )
                Button(
                    onClick = {

                    }
                ) {
                    Text(text = "Download")
                }
            }
        }
        Text(
            text = state.details
        )
    }
}

@Composable
private fun AppDetailsScreenMockk() {
    AppDetailsScreen(
        onAction = {},
        state = AppDetailsState.Success(
            iconUrl = null,
            name = "FoodMood",
            developer = "MYnimef",
            details = "An app for food mood"
        )
    )
}

@Preview
@Composable
private fun AppDetailsScreenDarkPreview() = GitStorePreviewTheme(EColorTheme.DARK) {
    AppDetailsScreenMockk()
}

@Preview
@Composable
private fun AppDetailsScreenLightPreview() = GitStorePreviewTheme(EColorTheme.LIGHT) {
    AppDetailsScreenMockk()
}