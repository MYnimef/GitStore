package com.mynimef.gitstore.data.remote.models

import com.google.gson.annotations.SerializedName
import com.mynimef.gitstore.domain.IAppInfo

internal data class AppInfo(

    @SerializedName("app_name")
    override val appName: String,

    @SerializedName("description")
    override val description: String

): IAppInfo
