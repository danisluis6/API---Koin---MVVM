package com.vogo.superbrain.activities.splash

import android.content.Intent
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vogo.superbrain.R
import com.vogo.superbrain.activities.BaseActivity
import com.vogo.superbrain.activities.login.LoginActivity
import com.vogo.superbrain.databinding.ActivitySplashBinding
import io.reactivex.disposables.Disposable

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel: SplashViewModel

    private lateinit var disposable: Disposable

    override fun bindingView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
    }

    override fun attachViewModel() {
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        binding.viewModel = viewModel
    }

    override fun initAttributes() {
        viewModel.getConfigureApp()!!
        viewModel.getNextEvent().observe(this, Observer {
            Handler().postDelayed({

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

            }, 1000)
        })
    }

}
