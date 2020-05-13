package com.vogo.superbrain.activities.splash

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import android.view.SurfaceHolder
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vogo.lib.utils.AppUtils
import com.vogo.superbrain.R
import com.vogo.superbrain.activities.BaseActivity
import com.vogo.superbrain.activities.main.MainView
import com.vogo.superbrain.databinding.SplashViewBinding
import com.vogo.superbrain.dialog.DialogView
import com.vogo.superbrain.frameworks.storage.SharedPreference
import org.koin.core.KoinComponent
import org.koin.core.inject

class SplashView : BaseActivity(), KoinComponent, SurfaceHolder.Callback {

    private lateinit var binding: SplashViewBinding
    private lateinit var viewModel: SplashViewModel

    val context: Context by inject()
    val sharedPreference : SharedPreference by inject()

    private var mediaPlayer: MediaPlayer? = null
    private var surfaceHolder: SurfaceHolder? = null

    override fun bindingView() {
        binding = DataBindingUtil.setContentView(this, R.layout.splash_view)
        surfaceHolder = binding.videoSurfaceView.holder
        surfaceHolder!!.addCallback(this)
    }

    override fun attachViewModel() {
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        binding.viewModel = viewModel
    }

    override fun initAttributes() {
        viewModel.handleView(this)
        viewModel.getConfigureApp()

        viewModel.isCheckUpdated().observe(this, Observer {
            Handler().postDelayed(Runnable {
                navigateToUpdated(it)
            }, 3000)
        })
    }

    private fun navigateToUpdated(isForce: Boolean) {
        if (isFinishing) return

        if (!isForce || AppUtils.getVersionName(context) == sharedPreference.getValueString(
                SharedPreference.ANDROID_LATEST_VERSION)) {
            navigateHomeScreen()
        } else {
            DialogView.newInstance(context.getString(R.string.error_dialog_warning_new_versions), "OK", "Cancel")
                .let {
                    it.isCancelable = false
                    it.show(supportFragmentManager, DialogView::javaClass.name)
                }
        }
    }

    private fun navigateHomeScreen() {
        val intent = Intent(this, MainView::class.java)
        startActivity(intent)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        mediaPlayer = MediaPlayer.create(context,  R.raw.welcome);
        mediaPlayer!!.setDisplay(surfaceHolder);
        mediaPlayer!!.setVolume(0.0f,0.0f)
        mediaPlayer!!.setOnPreparedListener {
            it.start()
            it.isLooping = true
        }
    }
}
