package com.vogo.geographyintellect.utils

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import androidx.annotation.RequiresApi


object FontsOverride {

    var sEfFontStyle: Typeface? = null

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    fun setDefaultFont(context: Context) {
        sEfFontStyle = Typeface.createFromAsset(context.assets, "fonts/helveticaneuelight.ttf")
        replaceFont(sEfFontStyle)
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private fun replaceFont(newTypeface: Typeface?) {
        try {
            val staticField = Typeface::class.java
                .getDeclaredField("MONOSPACE")
            staticField.isAccessible = true
            staticField[null] = newTypeface
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }

}