package com.vogo.superbrain

import android.app.Application
import com.vogo.superbrain.frameworks.di.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VogoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@VogoApplication)
            modules(appModule, apiModule, engineModule, activityModule)
        }
    }

}