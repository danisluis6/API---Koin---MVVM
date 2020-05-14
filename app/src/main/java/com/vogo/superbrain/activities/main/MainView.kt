package com.vogo.superbrain.activities.main

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vogo.lib.api.entities.Board
import com.vogo.superbrain.R
import com.vogo.superbrain.activities.BaseActivity
import com.vogo.superbrain.databinding.MainViewBinding
import com.vogo.superbrain.dialog.DialogSignIn
import com.vogo.superbrain.dialog.DialogView
import com.vogo.superbrain.viewholder.BoardGroupieViewHolder
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainView : BaseActivity(), BoardGroupieViewHolder.OnTriggerBoardEventListener, KoinComponent {

    private lateinit var binding: MainViewBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var boardGroupieAdapter: GroupAdapter<GroupieViewHolder>

    val context: Context by inject()

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
                        it.isCancelable = false
                        it.show(supportFragmentManager, DialogSignIn::javaClass.name)
                    }
            }
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
        // TODO
    }
}