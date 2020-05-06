package com.vogo.superbrain.engine

import com.vogo.superbrain.utils.Utilities
import org.greenrobot.eventbus.EventBus
import org.koin.core.KoinComponent

open class BaseEngine :  KoinComponent {

    private var eventBus: EventBus? = null

    init {
        eventBus = EventBus.getDefault()
    }

     fun postToEventBus(event: Any) {
        if (eventBus != null) {
            try {
                eventBus?.post(event)
            } catch (e: Exception) {
                Utilities.log(BaseEngine::javaClass.name, e.message)
            }
        }
    }

    fun unregisterFromBus(obj: Any?) {
        if (eventBus != null && obj != null) {
            try {
                eventBus?.unregister(obj)
            } catch (e: java.lang.Exception) {
                Utilities.log(BaseEngine::javaClass.name, e.message)
            }
        }
    }

    fun registerToBus(obj: Any?) {
        if (eventBus != null && obj != null) {
            try {
                eventBus?.register(obj)
            } catch (e: java.lang.Exception) {
                Utilities.log(BaseEngine::javaClass.name, e.message)
            }
        }
    }

}