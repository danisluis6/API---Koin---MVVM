package com.vogo.superbrain.frameworks.service

import com.vogo.superbrain.frameworks.service.response.ResponseLoginMessage
import retrofit2.Response
import retrofit2.http.POST

interface ApiService {

    @POST("/api/v1.0/auth")
    fun login(): io.reactivex.Observable<Response<ResponseLoginMessage?>?>?
}