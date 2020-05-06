package com.vogo.lib.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BodyResponse {

    @SerializedName("base_url")
    @Expose
    val baseUrl: String? = null

    @SerializedName("android_min_version")
    @Expose
    val minVersion: String? = null

    @SerializedName("android_latest_version")
    @Expose
    val lastestVersion: String? = null

}