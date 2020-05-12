package com.vogo.lib.utils

import android.content.Context
import android.content.pm.PackageManager
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

}