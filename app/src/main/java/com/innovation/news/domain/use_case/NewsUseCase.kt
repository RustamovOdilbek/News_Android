package com.innovation.news.domain.use_case

import com.innovation.news.data.models.model.NewsModel

interface NewsUseCase {

    suspend operator fun invoke(): Result<List<NewsModel>>

}