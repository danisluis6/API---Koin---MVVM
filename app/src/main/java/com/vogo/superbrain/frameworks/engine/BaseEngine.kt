package com.vogo.superbrain.frameworks.engine

import com.vogo.superbrain.utils.Utilities
import org.greenrobot.eventbus.EventBus

abstract class BaseEngine {

    fun registerToBus(obj: Any?) {
        if (EventBus.getDefault() != null && obj != null) {
            try {
                EventBus.getDefault().register(obj)
            } catch (e: Exception) {
                Utilities.log(BaseEngine::class.java.simpleName, e.message)
            }
        }
    }

    fun unregisterFromBus(obj: Any?) {
        if (EventBus.getDefault() != null && EventBus.getDefault() != null) {
            try {
                EventBus.getDefault().unregister(obj)
            } catch (e: java.lang.Exception) {
                Utilities.log(BaseEngine::class.java.simpleName, e.message)
            }
        }
    }

    fun postToEventBus(event: Any) {
        if (EventBus.getDefault() != null) {
            try {
                EventBus.getDefault().post(event)
            } catch (e: java.lang.Exception) {
                Utilities.log(BaseEngine::class.java.simpleName, e.message)
            }
        }
    }

}