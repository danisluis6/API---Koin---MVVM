package com.vogo.superbrain.frameworks.service

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vogo.superbrain.R
import com.vogo.superbrain.frameworks.service.file.ExternalFile
import com.vogo.superbrain.frameworks.service.response.LoginResponse
import com.vogo.superbrain.frameworks.service.response.SplashResponse
import com.vogo.superbrain.utils.Utilities
import io.reactivex.Observable
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response
import java.io.IOException

class ApiResponse : KoinComponent {

    private val context : Context? by inject()

    companion object {
        const val LOGIN = "json/response_intlogin.json"
        const val CONFIG = "json/response_intconfig.json"

        const val CHARSET = "UTF-8"
    }

    fun config(): Observable<Response<SplashResponse?>?>? {
        val gson = Gson()
        try {
            val jsonDataString = context?.let { ExternalFile.getResponseInitConfig(it) }
//            gson.fromJson(jsonDataString, ResponseSplashMessage::class.java)
            return Observable.just(gson.fromJson(jsonDataString, SplashResponse::class.java)) as Observable<Response<SplashResponse?>?>
        } catch (exception: IOException) {
            Utilities.log(ApiResponse::javaClass.name, context!!.getString(R.string.error_parse_json_file))
        }
        return null
    }

    fun login(): Observable<Response<LoginResponse?>?>? {
        val gson = Gson()
        try {
            val jsonDataString = context?.let { ExternalFile.getResponseInitLogin(it) }
            return gson.fromJson<Observable<Response<LoginResponse?>?>>(
                jsonDataString,
                object : TypeToken<LoginResponse>() {}.type
            )
        } catch (exception: IOException) {
            Utilities.log(ApiResponse::javaClass.name, context!!.getString(R.string.error_parse_json_file))
        }
        return null
    }

}