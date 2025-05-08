package com.mynimef.gitstore.data.local

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.mynimef.gitstore.domain.AppStorage
import com.mynimef.gitstore.domain.models.Integration
import androidx.core.content.edit

class AppStorageImpl(
    context: Context
): AppStorage {

    private val masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val sharedPreferences = EncryptedSharedPreferences.create(
        "secure_prefs",
        masterKey,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun storeToken(integration: Integration, token: String) {
        when (integration.source) {
            Integration.Source.GITHUB -> storeGitHubToken(token)
            Integration.Source.GITLAB -> TODO()
            Integration.Source.BITBUCKET -> TODO()
        }
    }

    private fun storeGitHubToken(token: String) {
        sharedPreferences.edit { putString("github_token", token) }
    }

}