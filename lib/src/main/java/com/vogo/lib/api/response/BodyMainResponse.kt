package com.vogo.lib.api.response

import com.google.gson.annotations.SerializedName
import com.vogo.lib.api.entities.Board

data class BodyMainResponse (
    @SerializedName("boards") val boards: ArrayList<Board>? = null
)