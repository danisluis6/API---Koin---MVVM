package com.vogo.superbrain.activities.login

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vogo.lib.common.EventLive
import com.vogo.superbrain.R
import com.vogo.superbrain.frameworks.service.ApiResponse
import com.vogo.superbrain.frameworks.service.response.LoginResponse
import io.reactivex.observers.DisposableObserver
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response

class LoginViewModel : ViewModel(), KoinComponent {

    private val app: Application by inject()
    private val reponse: ApiResponse by inject()

    val titleLoginButton = ObservableField<String>()
    val titleLogo = ObservableField<String>()

    val enableLoginButton = ObservableBoolean(false)

    private val loginEvent = EventLive<String>()
    private val apiEvent = EventLive<DisposableObserver<Response<LoginResponse?>?>?>()

    companion object {
        const val DELAY_TIME = 1000
    }

    init {
        registerBus()
        initAttributes()
    }

    private fun registerBus() {
    }

    private fun initAttributes() {
        titleLoginButton.set(app.getString(R.string.global_label_login))
        titleLogo.set(app.getString(R.string.global_label_vogo))
        enableLoginButton.set(true)
    }

    fun getLoginEvent(): LiveData<String> = loginEvent

    fun getApiEvent(): LiveData<DisposableObserver<Response<LoginResponse?>?>?> = apiEvent

    fun onLoginButtonClick() {
        // TODO
    }

    override fun onCleared() {
        super.onCleared()
    }

}
