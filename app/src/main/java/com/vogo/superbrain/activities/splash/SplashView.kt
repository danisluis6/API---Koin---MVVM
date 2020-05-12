package com.vogo.superbrain.activities.splash

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vogo.lib.utils.AppUtils
import com.vogo.superbrain.R
import com.vogo.superbrain.activities.BaseActivity
import com.vogo.superbrain.databinding.SplashViewBinding
import com.vogo.superbrain.dialog.DialogView
import com.vogo.superbrain.frameworks.storage.SharedPreference
import org.koin.core.KoinComponent
import org.koin.core.inject

class SplashView : BaseActivity(), KoinComponent {

    private lateinit var binding: SplashViewBinding
    private lateinit var viewModel: SplashViewModel
    private lateinit var rocketAnimation: AnimationDrawable

    val context: Context by inject()
    val sharedPreference : SharedPreference by inject()

    override fun bindingView() {
        binding = DataBindingUtil.setContentView(this, R.layout.splash_view)
    }

    override fun attachViewModel() {
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        binding.viewModel = viewModel
    }

    override fun initAttributes() {
        viewModel.handleView(this)
        viewModel.getConfigureApp()

        binding.rocketThrust.apply {
            setBackgroundResource(R.drawable.rocket_thrust)
            rocketAnimation = background as AnimationDrawable
        }

        rocketAnimation.start()

        viewModel.isCheckUpdated().observe(this, Observer {
            Handler().postDelayed(Runnable {
                rocketAnimation.stop()
                navigateToUpdated(it)
            }, 1500)
        })
    }

    private fun navigateToUpdated(isForce: Boolean) {
        if (isFinishing) return

        if (!isForce || AppUtils.getVersionName(context) == sharedPreference.getValueString(
                SharedPreference.ANDROID_LATEST_VERSION)) {
            // TODO
        } else {
            DialogView.newInstance(context.getString(R.string.error_dialog_warning_new_versions), "OK", "Cancel")
                .let {
                    it.isCancelable = false
                    it.show(supportFragmentManager, DialogView::javaClass.name)
                }
        }
    }

}
