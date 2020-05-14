package com.vogo.superbrain.dialog

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.vogo.superbrain.R
import org.koin.core.KoinComponent
import org.koin.core.inject

class DialogSignInViewModel : ViewModel(), KoinComponent {

    private val app: Application by inject()

    val headLine = ObservableField<String>()

    init {
        headLine.set(app.getString(R.string.dialog_signin))
    }

    fun handleView() {

    }

}