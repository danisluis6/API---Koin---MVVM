package com.vogo.superbrain.frameworks.service.response

import com.vogo.lib.api.response.BodySplashResponse
import com.vogo.lib.api.response.HeaderResponse

data class LoginResponse (
    var header: HeaderResponse? = null,
    val bodySplash: BodySplashResponse? = null
)