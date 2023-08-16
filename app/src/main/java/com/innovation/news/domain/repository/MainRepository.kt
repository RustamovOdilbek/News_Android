package com.innovation.news.domain.repository

import com.innovation.news.data.models.entity.NewsEntity
import com.innovation.news.data.models.response.AllNewsResponse

interface MainRepository {

    //remote data source
    suspend fun getAllNewsRemote(): AllNewsResponse

    //local data source

    suspend fun insertNewsListLocal(newsEntityList: List<NewsEntity>)

    suspend fun getAllNewsLocal(): List<NewsEntity>

    suspend fun updateNewsLocal(newsEntity: NewsEntity)

    suspend fun getSavedNewsList(): List<NewsEntity>

    suspend fun getSearchNewsEntity(searchString: String): List<NewsEntity>

}