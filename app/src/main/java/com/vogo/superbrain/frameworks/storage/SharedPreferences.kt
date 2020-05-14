package com.vogo.superbrain.frameworks.storage

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(val context: Context) {

    companion object {
        const val SHARED_PREFERENCE = "_sharedPref"

        const val BASE_URL = "base_url"
        const val ANDROID_MIN_VERSION = "android_min_version"
        const val ANDROID_LATEST_VERSION = "android_latest_version"

        const val TOKEN = "token"
    }

    val sharedPref: SharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE,
        Context.MODE_PRIVATE)

    fun put(KEY_NAME: String, text: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putString(KEY_NAME, text)
        editor.apply()
    }

    fun put(KEY_NAME: String, value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putInt(KEY_NAME, value)
        editor.apply()
    }

    fun put(KEY_NAME: String, status: Boolean) {
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putBoolean(KEY_NAME, status)
        editor.apply()
    }

    fun getValueString(KEY_NAME: String): String? {
        return sharedPref.getString(KEY_NAME, null)
    }

    fun getValueInt(KEY_NAME: String): Int {
        return sharedPref.getInt(KEY_NAME, 0)
    }

    fun getValueBoolean(KEY_NAME: String): Boolean {
        return sharedPref.getBoolean(KEY_NAME, false)
    }

    fun clear() {
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.clear()
        editor.apply()
    }

    fun clearValue(KEY_NAME: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.remove(KEY_NAME)
        editor.apply()
    }
}