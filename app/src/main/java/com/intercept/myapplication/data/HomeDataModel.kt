package com.intercept.myapplication.data

import com.google.gson.annotations.SerializedName

data class HomeDataModel(
    @SerializedName("id")
    val id: String,

    @SerializedName("imageUrl")
    val imageUrl: String
)
