package com.innovation.news.data.mapper

import com.innovation.news.data.models.model.NewsModel
import com.innovation.news.data.models.response.Article

object Mapper{

    fun taskRemoteToNewsModel(value: Article) = NewsModel(
        author = value.author.toString(),
        title = value.title.toString(),
        url = value.url.toString(),
        urlToImage = value.urlToImage.toString(),
        publishedAt = value.publishedAt.toString()
    )

}