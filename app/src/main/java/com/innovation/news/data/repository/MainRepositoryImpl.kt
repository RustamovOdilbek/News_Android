package com.innovation.news.data.repository

import com.innovation.news.data.models.entity.NewsEntity
import com.innovation.news.data.models.response.AllNewsResponse
import com.innovation.news.domain.datasource.local.LocalDataSource
import com.innovation.news.domain.datasource.remote.RemoteDataSource
import com.innovation.news.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MainRepository {

    //remote data source

    override suspend fun getAllNewsRemote(): AllNewsResponse = remoteDataSource.getAllNewsRemote()

    //local data source

    override suspend fun insertNewsListLocal(newsEntityList: List<NewsEntity>) = localDataSource.insertNewsListLocal(newsEntityList)

    override suspend fun getAllNewsLocal() = localDataSource.getAllNewsLocal()

    override suspend fun updateNewsLocal(newsEntity: NewsEntity) = localDataSource.updateNewsLocal(newsEntity)

    override suspend fun getSavedNewsList() = localDataSource.getSavedNewsList()

    override suspend fun getSearchNewsEntity(searchString: String): List<NewsEntity> = localDataSource.getSearchNewsEntity(searchString)


}