package com.vogo.geographyintellect.activities.main

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.vogo.lib.api.entities.Board
import com.vogo.geographyintellect.R
import com.vogo.geographyintellect.activities.BaseActivity
import com.vogo.geographyintellect.activities.profile.ProfileView
import com.vogo.geographyintellect.databinding.MainViewBinding
import com.vogo.geographyintellect.dialog.DialogSignIn
import com.vogo.geographyintellect.frameworks.storage.SharedPreference
import com.vogo.geographyintellect.viewholder.BoardGroupieViewHolder
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainView : BaseActivity(), BoardGroupieViewHolder.OnTriggerBoardEventListener, KoinComponent {

    private lateinit var binding: MainViewBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var boardGroupieAdapter: GroupAdapter<GroupieViewHolder>

    val context: Context by inject()
    val sharedPreferences: SharedPreference by inject()

    override fun bindingView() {
        binding = DataBindingUtil.setContentView(this, R.layout.main_view)
        boardGroupieAdapter = GroupAdapter()

        binding.rcvBoardGame.apply {
            adapter = boardGroupieAdapter
        }
    }

    override fun attachViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
    }

    override fun initAttributes() {
        viewModel.handleView(this)

        binding.avatar.load(sharedPreferences.getValueString(SharedPreference.AVATAR)) {
            crossfade(true)
            scale(Scale.FIT)
            size(width = 48, height = 48)
            placeholder(R.drawable.ic_mtrl_checked_circle)
            transformations(CircleCropTransformation())
        }

        viewModel.getBoardList().observe(this, Observer {
            if (it.isNullOrEmpty()) {
                return@Observer
            }
            val mainConfLineDetails: List<Board> = it.listIterator().asSequence().toList()
            boardGroupieAdapter.update(generateSmartHomeConfiguredGroupieItems(mainConfLineDetails))
        })

        viewModel.isDialogRegister().observe(this, Observer { it ->
            if (it) {
                DialogSignIn.newInstance()
                    .let {
                        it.isCancelable = true
                        it.show(supportFragmentManager, DialogSignIn::javaClass.name)
                    }
            }
        })

        viewModel.nextProfileScreen.observe(this, Observer {
            val intent = Intent(this, ProfileView::class.java)
            startActivity(intent)
        })
    }

    private fun generateSmartHomeConfiguredGroupieItems(list: List<Board>): MutableList<BoardGroupieViewHolder> {
        val mutableList = mutableListOf<BoardGroupieViewHolder>()
        for (element in list) {
            mutableList.add(BoardGroupieViewHolder(element, this))
        }
        return mutableList
    }

    override fun onMore(position: Int, board: Board) {
        viewModel.authenticate()
    }

    override fun onPlay(position: Int, board: Board) {

    }
}