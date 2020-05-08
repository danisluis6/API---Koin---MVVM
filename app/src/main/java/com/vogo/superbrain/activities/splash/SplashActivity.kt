package com.vogo.superbrain.activities.splash

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vogo.superbrain.R
import com.vogo.superbrain.activities.BaseActivity
import com.vogo.superbrain.activities.login.LoginActivity
import com.vogo.superbrain.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel: SplashViewModel
    private lateinit var rocketAnimation: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun bindingView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
    }

    override fun attachViewModel() {
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        binding.viewModel = viewModel
    }

    override fun initAttributes() {
        viewModel.getConfigureApp()

        binding.rocketThrust.apply {
            setBackgroundResource(R.drawable.rocket_thrust)
            rocketAnimation = background as AnimationDrawable
        }

        rocketAnimation.start()

        viewModel.getNextEvent().observe(this, Observer {
            Handler().postDelayed(Runnable {
                rocketAnimation.stop()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }, 2000)
        })
    }

}
