package com.innovation.news.domain.datasource.remote

import com.innovation.news.data.models.response.AllNewsResponse

interface RemoteDataSource {

    suspend fun getAllNews(): AllNewsResponse

}