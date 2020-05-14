package com.vogo.superbrain.viewholder

import coil.api.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.vogo.lib.api.entities.Board
import com.vogo.superbrain.R
import com.vogo.superbrain.databinding.BoardViewBinding
import com.xwray.groupie.databinding.BindableItem

class BoardGroupieViewHolder(private val board: Board,
                             private val triggerBoardEventListener: OnTriggerBoardEventListener) : BindableItem<BoardViewBinding>() {

    override fun getLayout(): Int = R.layout.board_view

    override fun bind(viewBinding: BoardViewBinding, position: Int) {
        initView()
        updateItemView(viewBinding, position)
    }

    private fun initView() {

    }

    private fun updateItemView(viewBinding: BoardViewBinding, position: Int) {
        viewBinding.imgContainer.load(board.container) {
            crossfade(true)
            scale(Scale.FIT)
            size(width = 48, height = 48)
            placeholder(R.drawable.ic_mtrl_checked_circle)
            transformations(CircleCropTransformation())
        }
        viewBinding.thumbnail.load(board.thumbnail) {
            crossfade(true)
            scale(Scale.FIT)
            placeholder(R.drawable.ic_mtrl_checked_circle)
        }
        viewBinding.title.text = board.title
        viewBinding.subtitle.text = board.sub_title
        viewBinding.description.text = board.description
        viewBinding.more.setOnClickListener {
            triggerBoardEventListener.onMore(position, board)
        }
        viewBinding.play.setOnClickListener {
            triggerBoardEventListener.onPlay(position, board)
        }
    }

    interface OnTriggerBoardEventListener {
        fun onMore(position: Int, board: Board)
        fun onPlay(position: Int, board: Board)
    }

}
