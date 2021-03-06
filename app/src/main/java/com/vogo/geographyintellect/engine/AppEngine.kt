package com.vogo.geographyintellect.engine

import com.vogo.geographyintellect.frameworks.service.ApiService
import com.vogo.geographyintellect.frameworks.service.response.SplashResponse
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppEngine : BaseEngine(), KoinComponent {

    val apiService : ApiService by inject()

    fun configure() {
        apiService.configure().enqueue(object : Callback<SplashResponse>{
            override fun onResponse(call: Call<SplashResponse>, response: Response<SplashResponse>) {
                postToEventBus(response)
            }
            override fun onFailure(call: Call<SplashResponse>, t: Throwable) {
                postToEventBus(call)
            }
        })
    }

}