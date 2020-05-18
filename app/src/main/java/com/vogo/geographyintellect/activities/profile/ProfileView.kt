package com.vogo.geographyintellect.activities.profile

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import coil.api.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.vogo.geographyintellect.R
import com.vogo.geographyintellect.activities.BaseActivity
import com.vogo.geographyintellect.databinding.ProfileViewBinding
import org.koin.core.KoinComponent

class ProfileView : BaseActivity(), KoinComponent {

    private lateinit var binding: ProfileViewBinding
    private lateinit var viewModel: ProfileViewModel

    override fun bindingView() {
        binding = DataBindingUtil.setContentView(this, R.layout.profile_view)
    }

    override fun attachViewModel() {
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.viewModel = viewModel
    }

    override fun initAttributes() {
        val host = NavHostFragment.create(R.navigation.nav_graph)
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, host)
            .setPrimaryNavigationFragment(host).commit()

        viewModel.getAvatar().observe(this, Observer {
            binding.navView.avatar.load(it) {
                crossfade(true)
                scale(Scale.FIT)
                placeholder(R.drawable.ic_mtrl_checked_circle)
                transformations(CircleCropTransformation())
            }
        })
    }
}