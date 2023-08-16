package com.innovation.news.domain.use_case.impl

import com.innovation.news.data.mapper.Mapper
import com.innovation.news.data.models.entity.NewsEntity
import com.innovation.news.data.models.model.NewsModel
import com.innovation.news.domain.repository.MainRepository
import com.innovation.news.domain.use_case.NewsUseCase
import javax.inject.Inject

class NewsUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    NewsUseCase {

    override suspend fun invoke(): Result<Unit> {
        return try {
            val response = mainRepository.getAllNewsRemote()

            val newsEntitys = response.articles.map { newsRemote ->
                Mapper.newsRemoteToNewsEntity(newsRemote)
            }

            mainRepository.insertNewsListLocal(newsEntitys)
            Result.success(Unit)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    override suspend fun getAllNewsLocal(): Result<List<NewsModel>> {
        return try {
            val response = mainRepository.getAllNewsLocal()

            val newsModels = response.map { newsLocal ->
                Mapper.newsLocalToNewsModel(newsLocal)
            }

            Result.success(newsModels)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    override suspend fun updateNewsLocal(newsModel: NewsModel) {
        val newsEntity = Mapper.newsModelToNewsLocal(newsModel)
        mainRepository.updateNewsLocal(newsEntity)

    }

    override suspend fun getSavedNewsList(): Result<List<NewsModel>> {
        return try {
            val response = mainRepository.getSavedNewsList()

            val newsModels = response.map { newsLocal ->
                Mapper.newsLocalToNewsModel(newsLocal)
            }

            Result.success(newsModels)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }


    override suspend fun getSearchNewsEntity(searchString: String): Result<List<NewsModel>>{
        return try {
            val response = mainRepository.getSearchNewsEntity(searchString)

            val newsModels = response.map { newsLocal ->
                Mapper.newsLocalToNewsModel(newsLocal)
            }

            Result.success(newsModels)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

}