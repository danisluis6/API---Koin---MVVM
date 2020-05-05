package com.vogo.superbrain.activities.splash

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vogo.lib.common.EventLive
import com.vogo.superbrain.frameworks.service.ApiService
import com.vogo.superbrain.frameworks.service.response.ResponseSplashMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response
import retrofit2.Retrofit

class SplashViewModel : ViewModel(), KoinComponent {

    val sloganTitle = ObservableField<String>()

    private val nextEvent = EventLive<String>()

    val retrofit: Retrofit by inject()

    init {
        registerBus()
        initAttributes()
    }

    private fun registerBus() {
    }

    private fun initAttributes() {
    }

    fun getNextEvent(): LiveData<String> = nextEvent

    fun getConfigureApp() : DisposableObserver<Response<ResponseSplashMessage?>?>? {
        return  retrofit.create(ApiService::class.java).configure()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableObserver<Response<ResponseSplashMessage?>?>() {
                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                    // TODO
                }

                override fun onNext(t: Response<ResponseSplashMessage?>) {
                    Log.i("TAG", t.message())
                }
            })
    }

    override fun onCleared() {
        super.onCleared()
    }

}
