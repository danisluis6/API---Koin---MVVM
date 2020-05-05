package com.vogo.superbrain.frameworks.service

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vogo.superbrain.R
import com.vogo.superbrain.frameworks.service.response.ResponseLoginMessage
import com.vogo.superbrain.utils.Utilities
import io.reactivex.Observable
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

class ApiResponse : KoinComponent {

    private val context : Context? by inject()

    fun login(): Observable<Response<ResponseLoginMessage?>?>? {
        val gson = Gson()
        try {
            val jsonDataString = convertJsonToString()
            return gson.fromJson<Observable<Response<ResponseLoginMessage?>?>>(
                jsonDataString,
                object : TypeToken<ArrayList<ResponseLoginMessage?>?>() {}.type
            )
        } catch (exception: IOException) {
            Utilities.log(ApiResponse::javaClass.name, context!!.getString(R.string.error_parse_json_file))
        }
        return null
    }

    @Throws(IOException::class)
    private fun convertJsonToString(): String {
        var inputStream: InputStream? = null
        val builder = StringBuilder()
        try {
            var jsonDataString: String?
            inputStream = context!!.assets.open("json/response_intlogin.json")
            val bufferedReader =
                BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            while (bufferedReader.readLine().also { jsonDataString = it } != null) {
                builder.append(jsonDataString)
            }
        } finally {
            inputStream?.close()
        }
        return String(builder)
    }
}