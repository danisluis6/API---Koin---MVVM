package com.vogo.geographyintellect.activities.profile

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.vogo.geographyintellect.R
import com.vogo.geographyintellect.engine.AppEngine
import com.vogo.geographyintellect.frameworks.service.ApiResponse
import com.vogo.geographyintellect.frameworks.storage.SharedPreference
import com.vogo.lib.common.EventLive
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProfileViewModel : ViewModel(), KoinComponent {

    val context: Context by inject()
    val appEngine : AppEngine by inject()
    val apiResponse : ApiResponse by inject()
    val sharedPreference: SharedPreference by inject()

    val uploadText = ObservableField<String>()
    val headLine = ObservableField<String>()

    val loadAvatar = EventLive<String>()

    init {
        registerBus()
        updateAccountInfo()
    }

    private fun updateAccountInfo() {
        uploadText.set(context.getString(R.string.profile_upload_title))
        loadAvatar.postValue(sharedPreference.getValueString(SharedPreference.AVATAR))
        headLine.set(context.getString(R.string.profile_title))
    }

    private fun registerBus() {
        appEngine.registerToBus(this)
        apiResponse.registerToBus(this)
    }

    fun loadAvatar() : EventLive<String> = loadAvatar
}