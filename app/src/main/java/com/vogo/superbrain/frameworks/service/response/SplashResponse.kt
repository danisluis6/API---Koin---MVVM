package com.vogo.superbrain.frameworks.service.response

import com.google.gson.annotations.SerializedName
import com.vogo.lib.api.response.BodyResponse
import com.vogo.lib.api.response.HeaderResponse

data class SplashResponse (
    @SerializedName("header") val header: HeaderResponse? = null,
    @SerializedName("body") val body: BodyResponse? = null
)