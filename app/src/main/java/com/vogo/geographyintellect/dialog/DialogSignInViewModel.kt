package com.vogo.geographyintellect.dialog

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.vogo.geographyintellect.R
import com.vogo.geographyintellect.utils.Utilities
import com.vogo.lib.api.constant.Constants
import com.vogo.lib.common.EventLive
import org.koin.core.KoinComponent
import org.koin.core.inject

class DialogSignInViewModel : ViewModel(), KoinComponent {

    private val app: Application by inject()

    private var googleSignIn = EventLive<String>()
    private var facebookSignIn = EventLive<String>()
    private var shouldShowProgress = EventLive<Boolean>()
    private var behaviorDialog = EventLive<Boolean>()

    val headLine = ObservableField<String>()

    init {
        headLine.set(app.getString(R.string.dialog_signin))
    }

    fun handleView() {

    }

    fun getGoogleSignIn(): LiveData<String> = googleSignIn

    fun getFacebookSignIn(): LiveData<String> = facebookSignIn

    fun shouldShowProgress() : LiveData<Boolean> = shouldShowProgress

    fun behaviorDialog() : LiveData<Boolean> = behaviorDialog

    fun onGoogleSignIn() {
        shouldShowProgress.postValue(true)
        googleSignIn.postValue(Constants.GOOGLE)
    }

    fun onFacebookSignIn() {
        facebookSignIn.postValue(Constants.FACEBOOK)
    }

    fun handleResult(task: Task<GoogleSignInAccount>?) {
        try {
            val account: GoogleSignInAccount = task!!.getResult(ApiException::class.java)!!
            updateStatusUI()
            updateAccountInfo(account)
        } catch (e: ApiException) {
            Utilities.log(DialogSignInViewModel::javaClass.name, e.message)
        }
    }

    private fun updateStatusUI() {
        shouldShowProgress.postValue(false)
        behaviorDialog.postValue(true)
    }

    fun updateAccountInfo(account: GoogleSignInAccount) {
        val name:String = account.displayName.toString()

    }

}