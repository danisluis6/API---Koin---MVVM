package com.vogo.superbrain.frameworks.service

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vogo.superbrain.R
import com.vogo.superbrain.engine.BaseEngine
import com.vogo.superbrain.frameworks.service.file.ExternalFile
import com.vogo.superbrain.frameworks.service.response.LoginResponse
import com.vogo.superbrain.frameworks.service.response.MainResponse
import com.vogo.superbrain.frameworks.service.response.SplashResponse
import com.vogo.superbrain.utils.Utilities
import io.reactivex.Observable
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response
import java.io.IOException

class ApiResponse : BaseEngine(), KoinComponent {

    private val context : Context? by inject()

    companion object {
        const val CHARSET = "UTF-8"
        const val AS_LOGIN = "json/login.json"
        const val AS_CONFIG = "json/config.json"
        const val AS_INIT_BOARDGAME = "json/board.json"
    }

    fun config() {
        val gson = Gson()
        try {
            val jsonDataString = context?.let { ExternalFile.getResponseInitConfig(it) }
            val response : SplashResponse = gson.fromJson(jsonDataString, SplashResponse::class.java)
            postToEventBus(response)
        } catch (exception: IOException) {
            Utilities.log(
                ApiResponse::javaClass.name,
                context!!.getString(R.string.error_parse_json_file)
            )
        }
    }

    fun boardGame() {
        val gson = Gson()
        try {
            val jsonDataString = context?.let {
                ExternalFile.getResponseInitBoardGame(it)
            }
            val response : MainResponse = gson.fromJson(jsonDataString, MainResponse::class.java)
            postToEventBus(response)
        } catch (exception : IOException) {
            Utilities.log(
                ApiResponse::javaClass.name,
                context!!.getString(R.string.error_parse_json_file)
            )
        }
    }

    fun authenticate(): Observable<Response<LoginResponse?>?>? {
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