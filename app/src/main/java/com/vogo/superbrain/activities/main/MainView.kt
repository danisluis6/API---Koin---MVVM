package com.vogo.superbrain.activities.main

import androidx.databinding.DataBindingUtil
import com.vogo.superbrain.R
import com.vogo.superbrain.activities.BaseActivity
import com.vogo.superbrain.databinding.MainViewBinding

class MainView : BaseActivity() {

    private lateinit var binding: MainViewBinding

    override fun bindingView() {
        binding = DataBindingUtil.setContentView(this, R.layout.main_view)
    }

    override fun attachViewModel() {
    }

    override fun initAttributes() {
    }
}