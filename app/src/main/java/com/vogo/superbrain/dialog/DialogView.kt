package com.vogo.superbrain.dialog

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.vogo.lib.R
import com.vogo.superbrain.databinding.VogoDialogAlertBinding


class DialogView : DialogFragment() {

    private lateinit var binding: VogoDialogAlertBinding

    private var mOnPositiveListener: OnPositiveListener? = null

    private var mOnNegativeListener: OnNegativeListener? = null

    fun createDialog(message: String?, positive: String?, negative: String?): DialogView? {
        val frag = DialogView()
        val args = Bundle()
        args.apply {
            putString("message", message)
            putString("positive", positive)
            if (negative != null) {
                args.putString("negative", negative)
            }
        }
        frag.apply {
            arguments = args
            isCancelable = false
        }
        val binding: MyInfoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.vogo_dialog_alert, null, false
        )

        return frag
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.cloneInContext(
            ContextThemeWrapper(
                context,
                R.style.FontTheme
            )
        )
            .inflate(R.layout.vogo_dialog_alert, container, false)
        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawableResource(R.drawable.ef_dialog_bg)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

//    fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
//        super.onViewCreated(view!!, savedInstanceState)
//        tvMessage.setText(arguments!!.getString("message", Constants.EMPTY_STRING))
//        btnPositive.setText(arguments!!.getString("positive", Constants.EMPTY_STRING))
//        btnPositive.setOnClickListener(View.OnClickListener { v: View? ->
//            if (mOnPositiveListener != null) {
//                mOnPositiveListener!!.onPositiveListener()
//            }
//            dismiss()
//        })
//        if (arguments!!.getString("negative") == null) {
//            btnNegative.setVisibility(View.GONE)
//        } else {
//            btnNegative.setVisibility(View.VISIBLE)
//            btnNegative.setText(arguments!!.getString("negative", Constants.EMPTY_STRING))
//            btnNegative.setOnClickListener(View.OnClickListener {
//                if (mOnNegativeListener != null) {
//                    mOnNegativeListener!!.onNegativeListener()
//                }
//                dismiss()
//            })
//        }
//    }

    override fun show(
        manager: FragmentManager,
        tag: String?
    ) {
        if (activity != null && activity!!.isFinishing) {
            return
        }
        val ft = manager.beginTransaction()
        ft.add(this, tag)
        ft.commitAllowingStateLoss()
    }

    interface OnPositiveListener {
        fun onPositiveListener()
    }

    interface OnNegativeListener {
        fun onNegativeListener()
    }

    fun setOnPositiveListener(onPositiveListener: OnPositiveListener?) {
        mOnPositiveListener = onPositiveListener
    }

    fun setOnNegativeListener(onNegativeListener: OnNegativeListener?) {
        mOnNegativeListener = onNegativeListener
    }

}