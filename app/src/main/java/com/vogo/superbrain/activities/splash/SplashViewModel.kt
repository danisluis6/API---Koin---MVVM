package com.vogo.superbrain.activities.splash

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vogo.lib.api.constant.Constants
import com.vogo.lib.api.response.BodySplashResponse
import com.vogo.lib.common.EventLive
import com.vogo.lib.utils.AppUtils
import com.vogo.superbrain.engine.AppEngine
import com.vogo.superbrain.frameworks.service.ApiResponse
import com.vogo.superbrain.frameworks.service.response.SplashResponse
import com.vogo.superbrain.frameworks.storage.SharedPreference
import org.greenrobot.eventbus.Subscribe
import org.koin.core.KoinComponent
import org.koin.core.inject

class SplashViewModel : ViewModel(), KoinComponent {

    val isCheckUpdated = EventLive<Boolean>()

    val context: Context by inject()
    val appEngine : AppEngine by inject()
    val apiResponse : ApiResponse by inject()
    val sharedPreference : SharedPreference by inject()

    private lateinit var mActivity: SplashView

    init {
        registerBus()
    }

    private fun registerBus() {
        appEngine.registerToBus(this)
        apiResponse.registerToBus(this)
    }

    fun handleView(activity: SplashView) {
        mActivity = activity
    }

    fun isCheckUpdated(): LiveData<Boolean> = isCheckUpdated

    fun getConfigureApp() {
        apiResponse.config()
    }

    @Subscribe
    fun onConfigureResponse(response: SplashResponse) {
        if (Constants.AS_SUCCESS == response.header!!.code) {
            saveSharePreference(response.body)

            val currentVersion = AppUtils.getVersionName(context)
            val minVersion = sharedPreference.getValueString(SharedPreference.ANDROID_MIN_VERSION)
            var latestVersion = sharedPreference.getValueString(SharedPreference.ANDROID_LATEST_VERSION)

            if (currentVersion!! < minVersion!!) {
                isCheckUpdated.postValue(true)
            } else {
                isCheckUpdated.postValue(false)
            }
        } else {
            // TODO
        }
    }

    private fun saveSharePreference(bodySplash: BodySplashResponse?) {
        bodySplash!!.baseUrl?.let { sharedPreference.put(SharedPreference.BASE_URL, it) }
        bodySplash.androidMinVersion?.let { sharedPreference.put(SharedPreference.ANDROID_MIN_VERSION, it) }
        bodySplash.androidMinVersion?.let { sharedPreference.put(SharedPreference.ANDROID_LATEST_VERSION, it) }
    }

    override fun onCleared() {
        appEngine.unregisterFromBus(this)
        apiResponse.unregisterFromBus(this)
        super.onCleared()
    }

}