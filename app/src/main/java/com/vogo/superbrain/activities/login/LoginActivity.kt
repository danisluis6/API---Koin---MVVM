package com.vogo.superbrain.activities.login

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vogo.superbrain.R
import com.vogo.superbrain.activities.BaseActivity
import com.vogo.superbrain.databinding.ActivityLoginBinding
import io.reactivex.disposables.Disposable

/**
 * http://www.androidcodefinder.com/blog/2018/07/30/awesome-login-screen-design-using-constraint-layout-in-android-studio/
 */

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var disposable: Disposable

    override fun bindingView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    override fun attachViewModel() {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewModel = viewModel
    }

    override fun initAttributes() {
        viewModel.getLoginEvent().observe(this, Observer {

        })

        viewModel.getApiEvent().observe(this, Observer {
            disposable = it!!
        })
    }

    override fun onDestroy() {
        if (disposable.isDisposed) disposable.dispose()
        super.onDestroy()
    }

}