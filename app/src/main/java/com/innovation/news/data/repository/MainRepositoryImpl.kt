package com.innovation.news.data.repository

import com.innovation.news.data.models.response.AllNewsResponse
import com.innovation.news.domain.datasource.local.LocalDataSource
import com.innovation.news.domain.datasource.remote.RemoteDataSource
import com.innovation.news.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MainRepository {

    override suspend fun getAllNews(): AllNewsResponse {
        return remoteDataSource.getAllNews()
    }

}