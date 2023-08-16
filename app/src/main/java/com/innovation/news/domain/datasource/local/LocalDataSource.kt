package com.innovation.news.domain.datasource.local

import com.innovation.news.data.models.entity.NewsEntity

interface LocalDataSource {

    suspend fun insertNewsListLocal(newsEntityList: List<NewsEntity>)

    suspend fun getAllNewsLocal(): List<NewsEntity>

    suspend fun updateNewsLocal(newsEntity: NewsEntity)

    suspend fun getSavedNewsList(): List<NewsEntity>

    suspend fun getSearchNewsEntity(searchString: String): List<NewsEntity>

}