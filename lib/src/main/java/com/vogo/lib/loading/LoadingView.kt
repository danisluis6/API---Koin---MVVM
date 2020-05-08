package com.vogo.lib.loading

import android.app.Activity
import android.app.Dialog
import android.view.Window
import com.vogo.lib.R

class VogoLoadingDialog(var activity: Activity?) : Dialog(activity!!) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawableResource(R.color.transparent)
        setContentView(R.layout.vogo_progress_dialog)
        setCancelable(false)
    }

    override fun show() {
        if (activity != null && !activity!!.isFinishing) {
            super.show()
        }
    }

    override fun dismiss() {
        if (activity != null && !activity!!.isFinishing) {
            super.dismiss()
        }
    }

}