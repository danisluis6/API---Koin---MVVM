package com.vogo.superbrain.frameworks.service

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vogo.superbrain.R
import com.vogo.superbrain.frameworks.service.response.ReadExternalFile
import com.vogo.superbrain.frameworks.service.response.ResponseLoginMessage
import com.vogo.superbrain.frameworks.service.response.ResponseSplashMessage
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

    fun config(): Observable<Response<ResponseSplashMessage?>?>? {
        val gson = Gson()
        try {
            val jsonDataString = context?.let { ReadExternalFile.getResponseInitConfig(it) }
//            gson.fromJson(jsonDataString, ResponseSplashMessage::class.java)
            return Observable.just(gson.fromJson(jsonDataString, ResponseSplashMessage::class.java)) as Observable<Response<ResponseSplashMessage?>?>
        } catch (exception: IOException) {
            Utilities.log(ApiResponse::javaClass.name, context!!.getString(R.string.error_parse_json_file))
        }
        return null
    }

    fun login(): Observable<Response<ResponseLoginMessage?>?>? {
        val gson = Gson()
        try {
            val jsonDataString = context?.let { ReadExternalFile.getResponseInitLogin(it) }
            return gson.fromJson<Observable<Response<ResponseLoginMessage?>?>>(
                jsonDataString,
                object : TypeToken<ResponseLoginMessage>() {}.type
            )
        } catch (exception: IOException) {
            Utilities.log(ApiResponse::javaClass.name, context!!.getString(R.string.error_parse_json_file))
        }
        return null
    }

}