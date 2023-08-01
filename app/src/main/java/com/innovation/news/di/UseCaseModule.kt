package com.innovation.news.di

import com.innovation.news.domain.use_case.NewsUseCase
import com.innovation.news.domain.use_case.impl.NewsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindTaskUseCase(useCase: NewsUseCaseImpl): NewsUseCase

}