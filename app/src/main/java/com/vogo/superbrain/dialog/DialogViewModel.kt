package com.vogo.superbrain.dialog

import android.app.Application
import android.os.Bundle
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vogo.lib.common.EventLive
import org.koin.core.KoinComponent
import org.koin.core.inject

class DialogViewModel : ViewModel(), KoinComponent {

    private val app: Application by inject()

    private var positiveEvent = EventLive<String>()
    private var negativeEvent = EventLive<String>()

    val textDialog = ObservableField<String>()
    val buttonPositiveText = ObservableField<String>()
    val buttonNegativeText = ObservableField<String>()

    fun handleView(bundle: Bundle?) {
        bundle.let {
            textDialog.set(it!!.get(DialogView.MESSAGE).toString())
            buttonPositiveText.set(it.get(DialogView.POSITIVE).toString())
            buttonNegativeText.set(it.get(DialogView.NEGATIVE).toString())
        }

    }

    fun getPositiveEvent(): LiveData<String> = positiveEvent

    fun getNegativeEvent(): LiveData<String> = negativeEvent

    fun onPositiveClick() {
        negativeEvent.postValue(DialogView.POSITIVE)
    }

    fun onNegativeClick() {
        negativeEvent.postValue(DialogView.NEGATIVE)
    }

}