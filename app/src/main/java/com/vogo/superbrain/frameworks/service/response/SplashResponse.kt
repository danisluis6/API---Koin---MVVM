package com.vogo.superbrain.frameworks.service.response

import com.vogo.lib.api.response.BodyResponse
import com.vogo.lib.api.response.HeaderResponse

data class SplashResponse (
    var header: HeaderResponse? = null,
    var body: BodyResponse? = null
)