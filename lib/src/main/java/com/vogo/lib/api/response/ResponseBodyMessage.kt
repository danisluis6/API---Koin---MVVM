package com.vogo.lib.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseBodyMessage {

    @SerializedName("email")
    @Expose
    val email: String? = null

    @SerializedName("password")
    @Expose
    val password: String? = null

}