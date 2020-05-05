package com.vogo.superbrain.activities.splash

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.vogo.superbrain.VogoApplication
import org.koin.core.KoinComponent
import org.koin.core.inject

class SplashViewModel : ViewModel(), KoinComponent {


    val app: VogoApplication by inject()

    val sloganTitle = ObservableField<String>()

    companion object {
        const val DELAY_TIME = 1000
    }

}
