package com.vogo.superbrain.activities.splash

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vogo.lib.common.EventLive
import com.vogo.superbrain.engine.AppEngine
import com.vogo.superbrain.frameworks.service.response.SplashResponse
import org.greenrobot.eventbus.Subscribe
import org.koin.core.KoinComponent
import org.koin.core.inject

class SplashViewModel : ViewModel(), KoinComponent {

    val sloganTitle = ObservableField<String>()

    val nextEvent = EventLive<String>()

    val appEngine : AppEngine by inject()

    init {
        registerBus()
        initAttributes()
    }

    private fun registerBus() {
        appEngine.registerToBus(this)
    }

    private fun initAttributes() {
    }

    fun getNextEvent(): LiveData<String> = nextEvent

    fun getConfigureApp() {
        appEngine.configure()
    }

    @Subscribe
    fun onConfigureResponse(response: SplashResponse) {
        Log.i("TAG", response.header!!.message)
    }

    override fun onCleared() {
        appEngine.unregisterFromBus(this)
        super.onCleared()
    }

}