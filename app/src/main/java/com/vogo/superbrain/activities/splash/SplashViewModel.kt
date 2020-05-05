package com.vogo.superbrain.activities.splash

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.vogo.superbrain.frameworks.engine.SplashEngine
import org.koin.core.KoinComponent
import org.koin.core.inject

class SplashViewModel : ViewModel(), KoinComponent {

    val sloganTitle = ObservableField<String>()
    private val engine: SplashEngine by inject()

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

    }

    override fun onCleared() {
        engine.unregisterFromBus(this)
        super.onCleared()
    }

}
