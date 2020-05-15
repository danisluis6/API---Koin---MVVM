package com.vogo.geographyintellect.utils

import com.vogo.geographyintellect.BuildConfig

object Utilities {

    fun log(tag: String?, message: String?) {
        if (BuildConfig.LOG) {
            android.util.Log.i(tag, message)
        }
    }

}