package com.vogo.superbrain.frameworks.service

import com.vogo.superbrain.frameworks.service.response.SplashResponse
import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("/configure")
    fun configure(): Call<SplashResponse>
}