package com.vogo.superbrain.frameworks.service.response

import com.vogo.lib.api.response.BodyResponse
import com.vogo.lib.api.response.HeaderResponse

data class LoginResponse (
    var header: HeaderResponse? = null,
    val body: BodyResponse? = null
)