package com.mynimef.gitstore.feature.main.presentation.utils

import androidx.compose.runtime.Composable
import com.mynimef.gitstore.common.uikit.R
import com.mynimef.gitstore.domain.models.Integration

/**
 *
 */
@Composable
internal fun Integration.getName(): String = when (this.source) {
    Integration.Source.GITHUB -> "GitHub"
    Integration.Source.GITLAB -> "GitLab"
    Integration.Source.BITBUCKET -> "BitBucket"
}

/**
 *
 */
@Composable
internal fun Integration.getAuthDescription(): String = when (this.auth) {
    Integration.Auth.PAT -> "PAT - Personal Access Token"
    Integration.Auth.OAUTH -> "OAuth2"
}

/**
 *
 */
@Composable
internal fun Integration.getIconRes() = when (this.source) {
    Integration.Source.GITHUB -> R.drawable.icon_github_dark
    Integration.Source.GITLAB -> R.drawable.icon_gitlab
    Integration.Source.BITBUCKET -> TODO()
}