package com.vogo.superbrain.dialog

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.vogo.superbrain.R
import com.vogo.superbrain.databinding.SigninViewBinding
import org.koin.core.KoinComponent
import org.koin.core.inject

class DialogSignIn : DialogFragment(), KoinComponent {

    companion object {
        fun newInstance() = DialogSignIn()
    }

    val mContext : Context by inject()

    private lateinit var binding: SigninViewBinding
    private lateinit var viewModel: DialogSignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(
                ContextThemeWrapper(
                    mContext,
                    R.style.FontTheme
                )
            ),
            R.layout.signin_view, container, false
        )
        binding.lifecycleOwner = this

        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawableResource(R.drawable.ef_dialog_bg)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DialogSignInViewModel::class.java)
        binding.viewModel = viewModel
        bindViewModelData()
    }

    private fun bindViewModelData() {
        viewModel.handleView()
    }

    override fun show (
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