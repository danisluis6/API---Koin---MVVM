package com.vogo.superbrain.activities.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vogo.lib.api.constant.Constants
import com.vogo.lib.api.response.BodyResponse
import com.vogo.lib.common.EventLive
import com.vogo.superbrain.engine.AppEngine
import com.vogo.superbrain.frameworks.service.ApiResponse
import com.vogo.superbrain.frameworks.service.response.SplashResponse
import com.vogo.superbrain.frameworks.storage.SharedPreference
import org.greenrobot.eventbus.Subscribe
import org.koin.core.KoinComponent
import org.koin.core.inject

class SplashViewModel : ViewModel(), KoinComponent {

    val nextEvent = EventLive<String>()

    val appEngine : AppEngine by inject()
    val apiResponse : ApiResponse by inject()
    val sharedPreference : SharedPreference by inject()

    init {
        registerBus()
        initAttributes()
    }

    private fun registerBus() {
        appEngine.registerToBus(this)
        apiResponse.registerToBus(this)
    }

    private fun initAttributes() {
    }

    fun getNextEvent(): LiveData<String> = nextEvent

    fun getConfigureApp() {
        apiResponse.config()
    }

    @Subscribe
    fun onConfigureResponse(response: SplashResponse) {
        if (Constants.AS_SUCCESS == response.header!!.code) {
            saveSharePreference(response.body)
            nextEvent.postValue(response.header.message)
        } else {

        }
    }

    private fun saveSharePreference(body: BodyResponse?) {
        body!!.baseUrl?.let { sharedPreference.put(SharedPreference.BASE_URL, it) }
        body.androidMinVersion?.let { sharedPreference.put(SharedPreference.ANDROID_MIN_VERSION, it) }
        body.androidMinVersion?.let { sharedPreference.put(SharedPreference.ANDROID_LATEST_VERSION, it) }
    }

    override fun onCleared() {
        appEngine.unregisterFromBus(this)
        apiResponse.unregisterFromBus(this)
        super.onCleared()
    }

}