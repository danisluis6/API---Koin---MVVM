package com.vogo.superbrain.frameworks.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vogo.lib.utils.TrustHTTPS
import com.vogo.superbrain.BuildConfig
import com.vogo.superbrain.frameworks.service.ApiResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val factoryModule = module {

    single<HttpLoggingInterceptor>(named("interceptor")) { HttpLoggingInterceptor() }

    single<OkHttpClient.Builder>(named("OkHttpClient")) {
        val interceptor: HttpLoggingInterceptor = get(named("interceptor"))
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
    }

    single<String>(named("baseUrl")) { BuildConfig.BASE_URL }

    single<TrustHTTPS>(named("trustHTTPs")) {
        val okHttpClient : OkHttpClient.Builder = get(named("OkHttpClient"))
        TrustHTTPS(okHttpClient)
    }

    single<Gson>(named("Gson")) {
        GsonBuilder()
            .setLenient()
            .create()
    }

    single<Retrofit> (named("retrofit")) {
        val trustHttps : TrustHTTPS = get(named("trustHTTPs"))
        val baseUrl : String = get(named("baseUrl"))
        trustHttps.intializeCertificate()
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(get(named("OkHttpClient")))
            .addConverterFactory(GsonConverterFactory.create(get(named("Gson"))))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

//    single<ApiService> (named("apiService")) {
//        (get(named("retrofit")) as Retrofit).create(ApiService::class.java)
//    }

    single { ApiResponse() }
}