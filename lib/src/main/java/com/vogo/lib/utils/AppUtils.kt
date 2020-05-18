package com.vogo.lib.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.DisplayMetrics
import android.view.inputmethod.InputMethodManager
import com.vogo.lib.api.constant.Constants

object AppUtils {

    fun getVersionName(context: Context): String? {
        try {
            val pInfo =
                context.packageManager.getPackageInfo(context.packageName, 0)
            return pInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return Constants.EMPTY_STRING
    }

    enum class Metrics {
        WIDTH, HEIGH
    }

    fun getDimension(activity: Activity, type: Metrics): Int {
        var size = 0
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        if (type == Metrics.WIDTH) size = displayMetrics.widthPixels
        if (type == Metrics.HEIGH) size = displayMetrics.heightPixels
        return size
    }

    fun hiddenKeyBoard(activity: Activity) {
        val view = activity.currentFocus
        if (view != null) {
            val imm =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}