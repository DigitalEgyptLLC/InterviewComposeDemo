package com.intercept.myapplication.ui.home

import android.content.Context
import com.intercept.myapplication.R
import com.intercept.myapplication.data.HomeDataModel

data class HomeUIModel(val titlePrefix: String, val imageUrl: String)

fun HomeDataModel.toUIModel(context: Context): HomeUIModel {
    val title = context.getString(R.string.home_item_title_prefix)
    return HomeUIModel(imageUrl = imageUrl, titlePrefix = title)
}