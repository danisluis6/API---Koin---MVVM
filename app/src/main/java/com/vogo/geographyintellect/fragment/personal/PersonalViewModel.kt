package com.vogo.geographyintellect.fragment.personal

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.vogo.lib.common.EventLive

class PersonalViewModel : ViewModel() {

    val keyboard = EventLive<String>()

    val email = ObservableField<String>()

    fun handleView(module: String) {

    }

    fun handleKeyboard() {
        keyboard.postValue(PersonalViewModel::javaClass.name)
    }

    fun getKeyboardEvent() : EventLive<String> = keyboard

}