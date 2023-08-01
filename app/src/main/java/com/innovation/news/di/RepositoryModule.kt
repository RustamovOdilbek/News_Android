package com.innovation.news.di

import com.innovation.news.data.repository.MainRepositoryImpl
import com.innovation.news.domain.datasource.local.LocalDataSource
import com.innovation.news.domain.datasource.remote.RemoteDataSource
import com.innovation.news.domain.datasource.storage.SecureStorage
import com.innovation.news.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): MainRepository = MainRepositoryImpl(localDataSource, remoteDataSource)


//    @Provides
//    @Singleton
//    fun provideAuthRepository(
//        remoteDataSource: RemoteDataSource,
//        secureStorage: SecureStorage
//    ): AuthRepository = AuthRepositoryImpl(remoteDataSource,secureStorage)

}