package com.vogo.geographyintellect

import android.app.Application
import com.vogo.geographyintellect.frameworks.di.module.apiModule
import com.vogo.geographyintellect.frameworks.di.module.appModule
import com.vogo.geographyintellect.frameworks.di.module.engineModule
import com.vogo.geographyintellect.utils.FontsOverride
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class VogoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@VogoApplication)
            modules(appModule, apiModule, engineModule)
        }
        FontsOverride.setDefaultFont(this)
    }
}