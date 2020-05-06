package com.vogo.superbrain.frameworks.service

import com.vogo.superbrain.frameworks.service.response.SplashResponse
import retrofit2.Call
import retrofit2.http.POST

interface ApiService {

    @POST("/configure")
    fun configure(): Call<SplashResponse>
}