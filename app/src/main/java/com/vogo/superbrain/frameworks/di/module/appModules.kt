package com.vogo.superbrain.frameworks.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vogo.lib.loading.VogoLoadingDialog
import com.vogo.lib.utils.TrustHTTPS
import com.vogo.superbrain.BuildConfig
import com.vogo.superbrain.activities.splash.SplashActivity
import com.vogo.superbrain.engine.AppEngine
import com.vogo.superbrain.frameworks.service.ApiResponse
import com.vogo.superbrain.frameworks.service.ApiService
import com.vogo.superbrain.frameworks.storage.SharedPreference
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single { return@single SharedPreference(get()) }
}

val apiModule = module {

    single {
        return@single HttpLoggingInterceptor()
    }

    single {
        val interceptor: HttpLoggingInterceptor = get()
        return@single OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
    }

    single { return@single BuildConfig.BASE_URL }

    single {
        val okHttpClient : OkHttpClient.Builder = get()
        return@single TrustHTTPS(okHttpClient)
    }

    single<Gson>() {
        return@single GsonBuilder()
            .setLenient()
            .create()
    }

    single<Retrofit> {
        val trustHttps : TrustHTTPS = get()
        val baseUrl : String = get()
        val client : OkHttpClient.Builder = get()
        trustHttps.intializeCertificate()
        return@single Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    single<ApiService> () {
        return@single (get() as Retrofit).create(ApiService::class.java)
    }

    single { return@single ApiResponse() }
}

val engineModule =  module {
    single<AppEngine> {
        return@single AppEngine()
    }
}

val activityModule = module {
    scope(named<SplashActivity>()) {
        factory { return@factory VogoLoadingDialog(get()) }
    }
}