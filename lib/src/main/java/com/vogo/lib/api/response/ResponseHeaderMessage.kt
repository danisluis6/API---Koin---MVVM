package com.vogo.lib.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseHeaderMessage {

    @SerializedName("code")
    @Expose
    private val code: Int? = null

    @SerializedName("message")
    @Expose
    private val message: String? = null

}