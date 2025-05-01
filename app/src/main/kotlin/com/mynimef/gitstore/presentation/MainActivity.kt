package com.mynimef.gitstore.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.mynimef.gitstore.presentation.appdetails.AppDetailsScreen
import com.mynimef.gitstore.presentation.appdetails.AppDetailsViewModel
import com.mynimef.gitstore.presentation.ui.theme.GitStoreTheme

internal class MainActivity : ComponentActivity() {

    private val viewModel: AppDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GitStoreTheme {
                AppDetailsScreen(
                    onAction = viewModel::onAction,
                    state = viewModel.appDetailsStateFlow.collectAsState().value
                )
                LaunchedEffect(Unit) {
                    viewModel.loadAppInfo(owner = "MYnimef", repo = "FoodMood-Android")
                }
            }
        }
    }

}