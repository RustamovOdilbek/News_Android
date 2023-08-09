package com.innovation.news.data.mapper

import com.innovation.news.data.models.entity.NewsEntity
import com.innovation.news.data.models.model.NewsModel
import com.innovation.news.data.models.response.Article

object Mapper{

    fun newsRemoteToNewsModel(value: Article) = NewsModel(
        author = value.author.toString(),
        title = value.title.toString(),
        url = value.url.toString(),
        urlToImage = value.urlToImage.toString(),
        content = value.content!!,
        publishedAt = value.publishedAt.toString()
    )

    fun newsRemoteToNewsEntity(value: Article) = NewsEntity(
        author = value.author.toString(),
        title = value.title.toString(),
        url = value.url.toString(),
        urlToImage = value.urlToImage.toString(),
        content = value.content.toString(),
        publishedAt = value.publishedAt.toString()
    )

    fun newsLocalToNewsModel(value: NewsEntity) = NewsModel(
        newsID = value.newsID,
        author = value.author,
        title = value.title,
        url = value.url,
        urlToImage = value.urlToImage,
        publishedAt = value.publishedAt,
        content = value.content,
        isSaved = value.isSaved
    )

    fun newsModelToNewsLocal(value: NewsModel) = NewsEntity(
        newsID = value.newsID,
        author = value.author,
        title = value.title,
        url = value.url,
        urlToImage = value.urlToImage,
        publishedAt = value.publishedAt,
        content = value.content,
        isSaved = value.isSaved
    )

}