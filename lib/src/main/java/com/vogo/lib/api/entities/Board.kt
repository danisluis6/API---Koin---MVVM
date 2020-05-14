package com.vogo.lib.api.entities

import com.google.gson.annotations.SerializedName

data class Board (
    @SerializedName("id") val id: Int? = null,
    @SerializedName("container") val container: String? = null,
    @SerializedName("thumbnail") val thumbnail: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("sub_title") val sub_title: String? = null,
    @SerializedName("media") val media: String? = null,
    @SerializedName("description") val description: String? = null
)