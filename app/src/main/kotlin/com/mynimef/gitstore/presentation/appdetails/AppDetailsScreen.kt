package com.mynimef.gitstore.presentation.appdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mynimef.gitstore.presentation.ui.theme.GitStoreTheme

@Composable
internal fun AppDetailsScreen(
    onAction: (AppDetailsAction) -> Unit,
    state: AppDetailsState
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        when (state) {
            AppDetailsState.Error -> {}
            AppDetailsState.Loading -> {}
            is AppDetailsState.Success -> SuccessScreen(
                state = state,
                modifier = Modifier.fillMaxSize().padding(innerPadding)
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

@Preview
@Composable
private fun AppDetailsScreenPreview() = GitStoreTheme {
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