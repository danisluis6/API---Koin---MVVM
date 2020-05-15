package com.vogo.geographyintellect.frameworks.service.response

import com.google.gson.annotations.SerializedName
import com.vogo.lib.api.response.BodyMainResponse
import com.vogo.lib.api.response.HeaderResponse

data class MainResponse (
    @SerializedName("header") val header: HeaderResponse? = null,
    @SerializedName("body") val body: BodyMainResponse? = null
)