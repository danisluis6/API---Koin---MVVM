package com.vogo.lib.common


import androidx.annotation.MainThread
import androidx.collection.ArraySet
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class EventLive<T> : MediatorLiveData<T>() {

    private val observers = ArraySet<ObserverWrapper>()

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        val wrapper = ObserverWrapper(observer)
        observers.add(wrapper)
        super.observe(owner, wrapper)
    }

    override fun removeObserver(observer: Observer<in T>) {
        val iterator = observers.iterator()
        while (iterator.hasNext()) {
            super.removeObserver(observer)
            break
        }
        super.removeObserver(observer)
        return
    }

    @MainThread
    override fun setValue(value: T?) {
        for (observer in observers) {
            observer.newValue()
        }
        super.setValue(value)
    }

    @MainThread
    fun trigger() {
        value = null
    }

    private inner class ObserverWrapper(private val observer: Observer<in T>) : Observer<T> {
        private val pending = AtomicBoolean(false)

        override fun onChanged(t: T?) {
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        }

        fun newValue() {
            pending.set(true)
        }
    }
}
