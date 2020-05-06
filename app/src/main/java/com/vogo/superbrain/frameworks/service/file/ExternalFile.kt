package com.vogo.superbrain.frameworks.service.file

import android.content.Context
import com.vogo.superbrain.frameworks.service.ApiResponse
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

object ExternalFile {

    fun getResponseInitLogin(context: Context): String {
        var inputStream: InputStream? = null
        val builder = StringBuilder()
        try {
            var jsonDataString: String?
            inputStream = context!!.assets.open(ApiResponse.LOGIN)
            val bufferedReader =
                BufferedReader(InputStreamReader(inputStream, ApiResponse.CHARSET))
            while (bufferedReader.readLine().also { jsonDataString = it } != null) {
                builder.append(jsonDataString)
            }
        } finally {
            inputStream?.close()
        }
        return String(builder)
    }

    fun getResponseInitConfig(context: Context): String {
        var inputStream: InputStream? = null
        val builder = StringBuilder()
        try {
            var jsonDataString: String?
            inputStream = context!!.assets.open(ApiResponse.CONFIG)
            val bufferedReader =
                BufferedReader(InputStreamReader(inputStream, ApiResponse.CHARSET))
            while (bufferedReader.readLine().also { jsonDataString = it } != null) {
                builder.append(jsonDataString)
            }
        } finally {
            inputStream?.close()
        }
        return String(builder)
    }

}