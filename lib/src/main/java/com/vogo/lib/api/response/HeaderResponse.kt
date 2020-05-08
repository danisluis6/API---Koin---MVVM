package com.vogo.lib.api.response

import com.google.gson.annotations.SerializedName

data class HeaderResponse (
    @SerializedName("code") val code: Int? = null,
    @SerializedName("message") val message: String? = null
)