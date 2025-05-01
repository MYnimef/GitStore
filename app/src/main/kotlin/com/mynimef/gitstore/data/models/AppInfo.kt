package com.mynimef.gitstore.data.models

import com.google.gson.annotations.SerializedName
import com.mynimef.gitstore.domain.IAppInfo

data class AppInfo(

    @SerializedName("app_name")
    override val appName: String,

    @SerializedName("description")
    override val description: String

): IAppInfo
