package com.vogo.superbrain.frameworks.service.response

import com.vogo.lib.api.response.ResponseBodyLogin
import com.vogo.lib.api.response.ResponseHeaderMessage

data class ResponseLoginMessage(
    var header: ResponseHeaderMessage? = null,
    val body: ResponseBodyLogin? = null
)