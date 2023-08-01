package com.innovation.news.data.models.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsModel(
    val author: String,
    val title: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
) : Parcelable