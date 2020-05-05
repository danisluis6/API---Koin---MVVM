package com.vogo.superbrain.frameworks.di.module

import com.google.gson.GsonBuilder
import com.vogo.lib.utils.TrustHTTPS
import com.vogo.superbrain.BuildConfig
import com.vogo.superbrain.frameworks.engine.LoginEngine
import com.vogo.superbrain.frameworks.service.ApiResponse
import com.vogo.superbrain.frameworks.service.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

}

val factoryModule = module {

    single(named("interceptor")) { HttpLoggingInterceptor() }

    single {
        val interceptor: HttpLoggingInterceptor = get(named("interceptor"))
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true).build()
    }

    single {
        BuildConfig.BASE_URL
    }

    single {
        TrustHTTPS(get())
    }

    single {
        GsonBuilder()
            .setLenient()
            .create()
    }

    single {
        val trustHttps : TrustHTTPS = get()
        val baseUrl : String = get()
        trustHttps.intializeCertificate()
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    single {
        val retrofit : Retrofit = get()
        retrofit.create(ApiService::class.java)
    }

    single {
        ApiResponse()
    }
}

val networkModule = module {

}

val engineModule = module {
    factory {
        LoginEngine()
    }
}

val viewModelModules = module {

}