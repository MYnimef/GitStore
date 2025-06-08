package com.mynimef.gitstore.feature.main.presentation.screens.integrations

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.mynimef.gitstore.common.uikit.theme.EColorTheme
import com.mynimef.gitstore.common.uikit.theme.GitStorePreviewTheme
import com.mynimef.gitstore.domain.models.Integration
import com.mynimef.gitstore.feature.main.presentation.utils.getAuthDescription
import com.mynimef.gitstore.feature.main.presentation.utils.getIconRes
import com.mynimef.gitstore.feature.main.presentation.utils.getName

@Composable
internal fun IntegrationsScreen(
    viewModel: IntegrationsViewModel = hiltViewModel()
) {
    IntegrationsScreen(
        onAction = viewModel::onAction,
        state = viewModel.authStateFlow.collectAsState().value,
    )
}

@Composable
private fun IntegrationsScreen(
    onAction: (IntegrationsAction) -> Unit,
    state: IntegrationsState
) = Column {
    Button(
        onClick = {

        }
    ) {
        Text(text = "Back")
    }
    when (state) {
        is IntegrationsState.Loaded -> IntegrationScreenLoaded(
            onAction = onAction,
            state = state
        )
        IntegrationsState.Loading -> {}
    }
}

@Composable
private fun ColumnScope.IntegrationScreenLoaded(
    onAction: (IntegrationsAction) -> Unit,
    state: IntegrationsState.Loaded,
) {
    LazyColumn(
        modifier = Modifier.weight(1f)
    ) {
        items(state.connectedIntegrations) {
            Integration(
                onAction = onAction,
                state = it
            )
        }
    }
    AddIntegration(
        availableIntegrations = state.availableIntegrations,
        onAction = onAction
    )
}

@Composable
private fun Integration(
    onAction: (IntegrationsAction) -> Unit,
    state: IntegrationsState.Loaded.ConnectedIntegration,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(Color.White)
        .clickable {
            onAction(IntegrationsAction.OnConnectedIntegrationClick(type = state.integration))
        }
        .padding(horizontal = 16.dp, vertical = 8.dp)
    ,
    horizontalArrangement = Arrangement.spacedBy(16.dp)
) {
    Image(
        modifier = Modifier.size(64.dp),
        painter = painterResource(state.integration.getIconRes()),
        contentDescription = null
    )
    Column {
        Text(
            text = state.integration.getName()
        )
        Text(
            text = state.integration.getAuthDescription()
        )
    }
}

@Composable
private fun AddIntegration(
    availableIntegrations: List<IntegrationsState.Loaded.AvailableIntegration>,
    onAction: (IntegrationsAction) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
        ,
        onClick = {
            showDialog = true
        }
    ) {
        Text(text = "Add Integration")
    }
    if (showDialog) {
        Dialog(
            onDismissRequest = {
                showDialog = false
            },
            properties = DialogProperties()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                ,
            ) {
                itemsIndexed(items = availableIntegrations) { index, item ->
                    AvailableIntegrationOption(
                        integration = item.integration,
                        onAction = onAction
                    )
                    if (index < availableIntegrations.size - 1) {
                        HorizontalDivider(
                            modifier = Modifier.padding(start = 80.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AvailableIntegrationOption(
    integration: Integration,
    onAction: (IntegrationsAction) -> Unit,
    modifier: Modifier = Modifier,
) = Row(
    modifier = modifier
        .fillMaxWidth()
        .clickable(
            onClick = { onAction(IntegrationsAction.OnAvailableIntegrationClick(integration)) }
        )
        .padding(vertical = 8.dp, horizontal = 16.dp)
    ,
    horizontalArrangement = Arrangement.spacedBy(16.dp)
) {
    Image(
        modifier = Modifier.size(48.dp),
        painter = painterResource(integration.getIconRes()),
        contentDescription = null
    )
    Column {
        Text(
            text = integration.getName()
        )
        Text(
            text = integration.getAuthDescription()
        )
    }
}

@Composable
private fun IntegrationsScreenMockk() {
    IntegrationsScreen(
        onAction = {},
        state = IntegrationsState.Loaded(
            connectedIntegrations = listOf(
                IntegrationsState.Loaded.ConnectedIntegration(
                    integration = Integration(
                        source = Integration.Source.GITHUB,
                        auth = Integration.Auth.PAT
                    ),
                    username = ""
                ),
                IntegrationsState.Loaded.ConnectedIntegration(
                    integration = Integration(
                        source = Integration.Source.GITLAB,
                        auth = Integration.Auth.PAT
                    ),
                    username = ""
                )
            ),
            availableIntegrations = listOf(
                IntegrationsState.Loaded.AvailableIntegration(
                    integration = Integration(
                        source = Integration.Source.GITHUB,
                        auth = Integration.Auth.PAT
                    ),
                ),
                IntegrationsState.Loaded.AvailableIntegration(
                    integration = Integration(
                        source = Integration.Source.GITLAB,
                        auth = Integration.Auth.PAT
                    ),
                ),
            )
        )
    )
}

@Preview
@Composable
private fun IntegrationsScreenDarkPreview() = GitStorePreviewTheme(EColorTheme.DARK) {
    IntegrationsScreenMockk()
}

@Preview
@Composable
private fun IntegrationsScreenLightPreview() = GitStorePreviewTheme(EColorTheme.LIGHT) {
    IntegrationsScreenMockk()
}