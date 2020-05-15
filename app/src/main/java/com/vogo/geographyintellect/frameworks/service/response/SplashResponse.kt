package com.vogo.geographyintellect.frameworks.service.response

import com.google.gson.annotations.SerializedName
import com.vogo.lib.api.response.BodySplashResponse
import com.vogo.lib.api.response.HeaderResponse

data class SplashResponse (
    @SerializedName("header") val header: HeaderResponse? = null,
    @SerializedName("body") val body: BodySplashResponse? = null
)