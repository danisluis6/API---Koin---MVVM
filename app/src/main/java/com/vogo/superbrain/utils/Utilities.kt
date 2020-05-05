package com.vogo.superbrain.utils

import com.vogo.superbrain.BuildConfig

object Utilities {

    fun log(tag: String?, message: String?) {
        if (BuildConfig.LOG) {
            android.util.Log.i(tag, message)
        }
    }

}