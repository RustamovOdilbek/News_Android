package com.innovation.news.data.source.local.impl

import com.innovation.news.data.db.NewsDao
import com.innovation.news.data.models.entity.NewsEntity
import com.innovation.news.domain.datasource.local.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val newsDao: NewsDao): LocalDataSource {

    override suspend fun insertNewsListLocal(newsEntityList: List<NewsEntity>) = newsDao.insertNewsListLocal(newsEntityList)

    override suspend fun getAllNewsLocal() = newsDao.getAllNewsLocal()

    override suspend fun updateNewsLocal(newsEntity: NewsEntity) = newsDao.updateNewsLocal(newsEntity)

    override suspend fun getSavedNewsList() = newsDao.getSavedNewsList()

    override suspend fun getSearchNewsEntity(searchString: String): List<NewsEntity> = newsDao.getSearchNewsEntity(searchString)
}