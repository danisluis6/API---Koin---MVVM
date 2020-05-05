package com.vogo.superbrain.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView()
        attachViewModel()
        initAttributes()
    }

    protected abstract fun bindingView()

    protected abstract fun attachViewModel()

    protected abstract fun initAttributes()
}
