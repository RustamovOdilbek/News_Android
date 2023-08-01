package com.innovation.news.domain.repository

import com.innovation.news.data.models.response.AllNewsResponse

interface MainRepository {

    suspend fun getAllNews(): AllNewsResponse

}