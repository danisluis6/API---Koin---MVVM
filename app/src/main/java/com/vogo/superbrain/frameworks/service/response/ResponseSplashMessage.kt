package com.vogo.superbrain.frameworks.service.response

import com.vogo.lib.api.response.ResponseBodySplash
import com.vogo.lib.api.response.ResponseHeaderMessage

data class ResponseSplashMessage (
    var header: ResponseHeaderMessage? = null,
    var body: ResponseBodySplash? = null
)