package com.innovation.news.data.models.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsModel(
    val newsID: Long = 0,
    val author: String,
    val title: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
    var isSaved: Boolean = false
) : Parcelable