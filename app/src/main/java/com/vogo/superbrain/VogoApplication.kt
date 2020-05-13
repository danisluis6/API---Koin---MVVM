package com.vogo.superbrain

import android.app.Application
import com.vogo.superbrain.frameworks.di.module.activityModule
import com.vogo.superbrain.frameworks.di.module.apiModule
import com.vogo.superbrain.frameworks.di.module.appModule
import com.vogo.superbrain.frameworks.di.module.engineModule
import com.vogo.superbrain.utils.FontsOverride
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class VogoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@VogoApplication)
            modules(appModule, apiModule, engineModule, activityModule)
        }
        FontsOverride.setDefaultFont(this)
    }
}