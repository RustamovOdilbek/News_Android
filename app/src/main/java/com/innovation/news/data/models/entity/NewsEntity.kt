package com.innovation.news.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.innovation.news.common.Constants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val newsID: Long = 0,
    val author: String,
    val title: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
    val isSaved: Boolean = false
)