package com.vogo.superbrain.frameworks.service

import com.vogo.superbrain.frameworks.service.response.ResponseSplashMessage
import retrofit2.Response
import retrofit2.http.POST

interface ApiService {

    @POST("/configure")
    fun configure(): io.reactivex.Observable<Response<ResponseSplashMessage?>?>?
}