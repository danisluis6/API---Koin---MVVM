package com.vogo.superbrain.activities.login

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vogo.lib.common.EventLive
import com.vogo.superbrain.R
import com.vogo.superbrain.frameworks.engine.LoginEngine
import com.vogo.superbrain.frameworks.service.ApiResponse
import com.vogo.superbrain.frameworks.service.response.ResponseLoginMessage
import com.vogo.superbrain.utils.Utilities
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.Subscribe
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response

class LoginViewModel : ViewModel(), KoinComponent {

    private val app: Application by inject()
    private val reponse: ApiResponse by inject()
    private val engine: LoginEngine by inject()

    val titleLoginButton = ObservableField<String>()
    val titleLogo = ObservableField<String>()

    val enableLoginButton = ObservableBoolean(false)

    private val loginEvent = EventLive<String>()
    private val apiEvent = EventLive<DisposableObserver<Response<ResponseLoginMessage?>?>?>()

    companion object {
        const val DELAY_TIME = 1000
    }

    init {
        registerBus()
        initAttributes()
    }

    private fun registerBus() {
        engine.registerToBus(this)
    }

    private fun initAttributes() {
        titleLoginButton.set(app.getString(R.string.global_label_login))
        titleLogo.set(app.getString(R.string.global_label_vogo))
        enableLoginButton.set(true)
    }

    fun getLoginEvent(): LiveData<String> = loginEvent

    fun getApiEvent(): LiveData<DisposableObserver<Response<ResponseLoginMessage?>?>?> = apiEvent

    fun onLoginButtonClick() {
        apiEvent.value = login()
    }

    fun login(): DisposableObserver<Response<ResponseLoginMessage?>?>? {
        return reponse.login()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableObserver<Response<ResponseLoginMessage?>?>() {
                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                    // TODO
                }

                override fun onNext(t: Response<ResponseLoginMessage?>) {
                    engine.postToEventBus(t)
                }
            })
    }

    @Subscribe
    fun onLoginResponse(response: ResponseLoginMessage) {
        Utilities.log("TAG", response.body!!.email)
    }

    override fun onCleared() {
        engine.unregisterFromBus(this)
        super.onCleared()
    }

}
