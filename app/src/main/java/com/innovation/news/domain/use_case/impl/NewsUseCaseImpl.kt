package com.innovation.news.domain.use_case.impl

import com.innovation.news.data.mapper.Mapper
import com.innovation.news.data.models.model.NewsModel
import com.innovation.news.domain.repository.MainRepository
import com.innovation.news.domain.use_case.NewsUseCase
import javax.inject.Inject

class NewsUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    NewsUseCase {

    override suspend fun invoke(): Result<List<NewsModel>> {
        return try {
            val response = mainRepository.getAllNews()
            val taskModels = response.articles.map { taskRemote ->
                Mapper.taskRemoteToNewsModel(taskRemote)
            }
            //save to db
            //mainRepository.saveTasksToDb(taskModels)
            Result.success(taskModels)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

}