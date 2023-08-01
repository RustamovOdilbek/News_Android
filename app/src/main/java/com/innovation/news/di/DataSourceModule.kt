package com.innovation.news.di

import com.innovation.news.common.SharedPref
import com.innovation.news.data.db.NewsDao
import com.innovation.news.data.network.ApiService
import com.innovation.news.data.source.local.impl.LocalDataSourceImpl
import com.innovation.news.data.source.remote.impl.RemoteDataSourceImpl
import com.innovation.news.data.source.storage.impl.SecureStorageImpl
import com.innovation.news.domain.datasource.local.LocalDataSource
import com.innovation.news.domain.datasource.remote.RemoteDataSource
import com.innovation.news.domain.datasource.storage.SecureStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(
        newsDao: NewsDao
    ): LocalDataSource = LocalDataSourceImpl(newsDao = newsDao)

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        apiService: ApiService
    ): RemoteDataSource = RemoteDataSourceImpl(apiService = apiService)

    @Provides
    @Singleton
    fun provideSecureStorage(
        sharedPref: SharedPref
    ): SecureStorage = SecureStorageImpl(sharedPref)

}