package com.vogo.superbrain.frameworks.customui

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaPlayer.OnVideoSizeChangedListener
import android.util.AttributeSet
import android.view.SurfaceView
import android.view.View

class VideoSurfaceView : SurfaceView, OnVideoSizeChangedListener {

    private var mVideoWidth = 0
    private var mVideoHeight = 0

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    /*
     * Set video size.
     *
     * @see MediaPlayer.getVideoWidth
     * @see MediaPlayer.getVideoHeight
     */
    fun setVideoSize(videoWidth: Int, videoHeight: Int) {
        mVideoWidth = videoWidth
        mVideoHeight = videoHeight
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var width = View.getDefaultSize(mVideoWidth, widthMeasureSpec)
        var height = View.getDefaultSize(mVideoHeight, heightMeasureSpec)
        if (mVideoWidth > 0 && mVideoHeight > 0) {
            val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
            val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)
            val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)
            val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)
            if (widthSpecMode == MeasureSpec.EXACTLY && heightSpecMode == MeasureSpec.EXACTLY) {
                width = widthSpecSize
                height = heightSpecSize

                if (mVideoWidth * height < width * mVideoHeight) {
                    width = height * mVideoWidth / mVideoHeight
                } else if (mVideoWidth * height > width * mVideoHeight) {
                    height = width * mVideoHeight / mVideoWidth
                }
            } else if (widthSpecMode == MeasureSpec.EXACTLY) {
                width = widthSpecSize
                height = width * mVideoHeight / mVideoWidth
                if (heightSpecMode == MeasureSpec.AT_MOST && height > heightSpecSize) {
                    height = heightSpecSize
                }
            } else if (heightSpecMode == MeasureSpec.EXACTLY) {
                height = heightSpecSize
                width = height * mVideoWidth / mVideoHeight
                if (widthSpecMode == MeasureSpec.AT_MOST && width > widthSpecSize) {
                    width = widthSpecSize
                }
            } else {
                width = mVideoWidth
                height = mVideoHeight
                if (heightSpecMode == MeasureSpec.AT_MOST && height > heightSpecSize) {
                    height = heightSpecSize
                    width = height * mVideoWidth / mVideoHeight
                }
                if (widthSpecMode == MeasureSpec.AT_MOST && width > widthSpecSize) {
                    width = widthSpecSize
                    height = width * mVideoHeight / mVideoWidth
                }
            }
        }
        setMeasuredDimension(width, height)
    }

    override fun onVideoSizeChanged(mp: MediaPlayer, width: Int, height: Int) {
        mVideoWidth = mp.videoWidth
        mVideoHeight = mp.videoHeight
        if (mVideoWidth != 0 && mVideoHeight != 0) {
            holder.setFixedSize(mVideoWidth, mVideoHeight)
            requestLayout()
        }
    }
}