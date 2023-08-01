package com.innovation.news.data.network

import com.innovation.news.data.models.response.AllNewsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("v2/top-headlines?country=us&category=business&apiKey=dac197ed7e30405ca9e00f86adfa4036")
    suspend fun getAllNews(): AllNewsResponse

}