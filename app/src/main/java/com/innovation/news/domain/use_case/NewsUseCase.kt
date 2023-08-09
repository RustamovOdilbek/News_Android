package com.innovation.news.domain.use_case

import com.innovation.news.data.models.entity.NewsEntity
import com.innovation.news.data.models.model.NewsModel

interface NewsUseCase {

    suspend operator fun invoke(): Result<Unit>

    suspend fun getAllNewsLocal(): Result<List<NewsModel>>

    suspend fun updateNewsLocal(newsModel: NewsModel)

    suspend fun getSavedNewsList(): Result<List<NewsModel>>

}