package com.vogo.geographyintellect.fragment.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vogo.geographyintellect.databinding.PersonalViewBinding
import com.vogo.lib.api.constant.Constants
import com.vogo.lib.utils.AppUtils
import org.koin.core.KoinComponent

class PersonalFragment : Fragment(), KoinComponent {

    private lateinit var binding: PersonalViewBinding
    private lateinit var viewModel:PersonalViewModel

    private lateinit var module: String

    companion object {
        const val ARG_CURRENT_MODULE = "module"
        const val ARG_PARAM_EDIT = "edit"

        fun newInstance(module: String, isEdit: Boolean) : PersonalFragment =
            PersonalFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CURRENT_MODULE, module)
                    putBoolean(ARG_PARAM_EDIT, isEdit)
                }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PersonalViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(PersonalViewModel::class.java)
        binding.viewModel = viewModel

        val bundle = arguments
        module = bundle?.getString(ARG_CURRENT_MODULE) ?: Constants.EMPTY_STRING

        bindViewModelData()
    }

    private fun bindViewModelData() {
        viewModel.handleView(module)

        viewModel.getKeyboardEvent().observe(this.viewLifecycleOwner, Observer {
            activity?.let { it1 -> AppUtils.hiddenKeyBoard(it1) }
        })
    }

}