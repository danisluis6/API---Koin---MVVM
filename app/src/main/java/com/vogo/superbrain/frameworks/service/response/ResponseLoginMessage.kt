package com.vogo.superbrain.frameworks.service.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vogo.lib.api.response.ResponseBodyMessage
import com.vogo.lib.api.response.ResponseHeaderMessage

class ResponseLoginMessage() : Parcelable {

    @SerializedName("header")
    @Expose
    val header: ResponseHeaderMessage? = null

    @SerializedName("body")
    @Expose
    val body: ResponseBodyMessage? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResponseLoginMessage> {
        override fun createFromParcel(parcel: Parcel): ResponseLoginMessage {
            return ResponseLoginMessage(parcel)
        }

        override fun newArray(size: Int): Array<ResponseLoginMessage?> {
            return arrayOfNulls(size)
        }
    }

}