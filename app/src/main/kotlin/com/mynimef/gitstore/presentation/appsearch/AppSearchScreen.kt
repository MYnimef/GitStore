package com.mynimef.gitstore.presentation.appsearch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mynimef.gitstore.presentation.ui.theme.GitStoreTheme

@Composable
fun AppSearchScreen(
    onAction: (AppSearchAction) -> Unit,
    state: AppSearchState,
    modifier: Modifier = Modifier
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
    verticalArrangement = Arrangement.Top
) {
    SearchBar()
    when (state) {
        AppSearchState.Error -> {}
        AppSearchState.Loading -> {}
        is AppSearchState.Success -> SuccessScreen(
            onAction = onAction,
            state = state
        )
    }
}

@Composable
private fun SearchBar(
    modifier: Modifier = Modifier
) = Column {
    Text(
        text = "Search"
    )
    var searchFieldState by rememberSaveable { mutableStateOf("") }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = searchFieldState,
        onValueChange = { searchFieldState = it }
    )
}

@Composable
private fun SuccessScreen(
    onAction: (AppSearchAction) -> Unit,
    state: AppSearchState.Success,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(state.apps) {
            AppInfo(
                onAction = onAction,
                state = it
            )
        }
    }
}

@Composable
private fun AppInfo(
    onAction: (AppSearchAction) -> Unit,
    state: AppSearchState.Success.AppInfo,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    state.iconUrl?.let {

    }
    Column {
        Text(
            text = state.name
        )
        Text(
            text = state.developer
        )
    }
    Button(
        onClick = {}
    ) {
        Text("Get it")
    }
}

@Preview
@Composable
private fun AppSearchScreenPreview() = GitStoreTheme {
    AppSearchScreen(
        onAction = {},
        state = AppSearchState.Success(
            apps = listOf(
                AppSearchState.Success.AppInfo(
                    iconUrl = null,
                    name = "FoddMood",
                    developer = "MYnimef"
                )
            )
        )
    )
}