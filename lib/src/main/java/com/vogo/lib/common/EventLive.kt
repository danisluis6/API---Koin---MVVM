package com.vogo.lib.common


import androidx.lifecycle.MutableLiveData

class EventLive<T> : MutableLiveData<T>() {

    fun <T> MutableLiveData<T>.trigger() {
        value = value
    }
}
