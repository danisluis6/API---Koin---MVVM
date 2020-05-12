package com.vogo.superbrain.dialog

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vogo.superbrain.R
import com.vogo.superbrain.databinding.DialogViewBinding
import org.koin.core.KoinComponent
import org.koin.core.inject


class DialogView : DialogFragment(), KoinComponent {

    companion object {
        const val MESSAGE = "message"
        const val POSITIVE = "positive"
        const val NEGATIVE = "negative"

        fun newInstance(message: String?, positive: String?, negative: String?) =
            DialogView().apply {
                arguments = Bundle().apply {
                    putString(MESSAGE, message)
                    putString(POSITIVE, positive)
                    putString(NEGATIVE, negative)
                }
            }
    }

    val mContext : Context by inject()

    private lateinit var binding: DialogViewBinding
    private lateinit var viewModel: DialogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(ContextThemeWrapper(
                mContext,
                R.style.FontTheme
            )),
            R.layout.dialog_view, container, false
        )
        binding.lifecycleOwner = this

        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawableResource(R.drawable.ef_dialog_bg)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DialogViewModel::class.java)
        binding.viewModel = viewModel
        val bundle = arguments
        bindViewModelData(bundle)
    }

    private fun bindViewModelData(bundle: Bundle?) {
        viewModel.handleView(bundle)

        viewModel.getPositiveEvent().observe(this.viewLifecycleOwner, Observer {

        })

        viewModel.getNegativeEvent().observe(this.viewLifecycleOwner, Observer {

        })
    }

    override fun show(
        manager: FragmentManager,
        tag: String?
    ) {
        if (activity != null && requireActivity().isFinishing) {
            return
        }
        val ft = manager.beginTransaction()
        ft.add(this, tag)
        ft.commitAllowingStateLoss()
    }

}