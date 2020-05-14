package com.vogo.superbrain.activities.main

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vogo.lib.api.constant.Constants
import com.vogo.lib.api.entities.Board
import com.vogo.lib.common.EventLive
import com.vogo.superbrain.engine.AppEngine
import com.vogo.superbrain.frameworks.service.ApiResponse
import com.vogo.superbrain.frameworks.service.response.MainResponse
import com.vogo.superbrain.frameworks.storage.SharedPreference
import org.greenrobot.eventbus.Subscribe
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainViewModel : ViewModel(), KoinComponent {

    val appEngine : AppEngine by inject()
    val apiResponse : ApiResponse by inject()
    val sharedPreference : SharedPreference by inject()

    val isDialogRegister = EventLive<Boolean>()

    private val boardList = MutableLiveData<ArrayList<Board>>()

    init {
        registerBus()
    }

    private fun registerBus() {
        appEngine.registerToBus(this)
        apiResponse.registerToBus(this)
    }

    fun handleView(mainView: MainView) {
        initBoardGame()
    }

    fun isDialogRegister(): LiveData<Boolean> = isDialogRegister

    fun initBoardGame() {
        apiResponse.boardGame()
    }

    @Subscribe
    fun onMainResponse(response: MainResponse) {
        if (Constants.AS_SUCCESS == response.header!!.code) {
            boardList.value = response.body!!.boards!!
        } else {

        }
    }

    fun getBoardList(): LiveData<ArrayList<Board>> = boardList

    fun authenticate() {
        if (TextUtils.isEmpty(sharedPreference.getValueString(SharedPreference.TOKEN))) {
            isDialogRegister.postValue(true)
        } else {
            apiResponse.authenticate()
        }
    }
}