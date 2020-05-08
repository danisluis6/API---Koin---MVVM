package com.vogo.lib.api.response

import com.google.gson.annotations.SerializedName

data class BodyResponse (
    @SerializedName("base_url") val baseUrl: String? = null,
    @SerializedName("android_min_version") val androidMinVersion: String? = null,
    @SerializedName("android_latest_version") val androidLastestVersion: String? = null
)