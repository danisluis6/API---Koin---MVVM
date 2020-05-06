package com.vogo.superbrain.engine

import com.vogo.superbrain.frameworks.service.ApiService
import com.vogo.superbrain.frameworks.service.response.SplashResponse
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppEngine : BaseEngine(), KoinComponent {

    val apiService : ApiService by inject()
    private var call : Call<SplashResponse>? = null

    fun configure() {
        call = apiService.configure()
        call!!.enqueue(object : Callback<SplashResponse>{
            override fun onResponse(call: Call<SplashResponse>, response: Response<SplashResponse>) {
                postToEventBus(response)
            }
            override fun onFailure(call: Call<SplashResponse>, t: Throwable) {
                postToEventBus(call)
            }
        })
    }

}