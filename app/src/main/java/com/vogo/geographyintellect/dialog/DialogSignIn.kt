package com.vogo.geographyintellect.dialog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.vogo.geographyintellect.R
import com.vogo.geographyintellect.databinding.SigninViewBinding
import com.vogo.lib.loading.ProgressView
import org.koin.core.KoinComponent
import org.koin.core.inject

class DialogSignIn : DialogFragment(), KoinComponent {

    companion object {
        const val RC_SIGN_IN = 9001
        fun newInstance() = DialogSignIn()
    }

    val mContext : Context by inject()

    private lateinit var binding: SigninViewBinding
    private lateinit var viewModel: DialogSignInViewModel
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var progressView: ProgressView

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
        initSignInWithGoogle()
        bindViewModelData()
    }

    fun initSignInWithGoogle() {
        val gso : GoogleSignInOptions = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        googleSignInClient = activity?.let { GoogleSignIn.getClient(it, gso) }!!
        progressView = ProgressView(activity)
    }

    private fun bindViewModelData() {
        viewModel.handleView()

        viewModel.getGoogleSignIn().observe(this.viewLifecycleOwner, Observer {
            val intent = googleSignInClient.signInIntent
            startActivityForResult(
                intent,
                RC_SIGN_IN
            )
        })

        viewModel.getFacebookSignIn().observe(this.viewLifecycleOwner, Observer {

        })

        viewModel.shouldShowProgress().observe(this.viewLifecycleOwner, Observer {
            if (it) {
                progressView.show()
            } else {
                progressView.hide()
            }
        })

        viewModel.behaviorDialog().observe(this.viewLifecycleOwner, Observer {
            if (it) {
                this.dismiss()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            viewModel.handleResult(task)
        }
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

/*
 * If you want to find event: android:onClick= and implement it into DialogSignInBinding
 * You should find it from by Ctrl + H (copy and paste)
 */
