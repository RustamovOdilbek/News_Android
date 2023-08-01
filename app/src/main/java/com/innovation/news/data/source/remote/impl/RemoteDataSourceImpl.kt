package com.innovation.news.data.source.remote.impl

import com.innovation.news.data.models.response.AllNewsResponse
import com.innovation.news.data.network.ApiService
import com.innovation.news.domain.datasource.remote.RemoteDataSource
import javax.inject.Inject

class RemoteDataSourceImpl@Inject constructor(private val apiService: ApiService) :
    RemoteDataSource {

    override suspend fun getAllNews(): AllNewsResponse {
        return apiService.getAllNews()
    }
}