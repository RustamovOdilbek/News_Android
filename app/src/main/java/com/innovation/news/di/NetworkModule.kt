package com.innovation.news.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.innovation.news.data.network.ApiService
import com.innovation.news.domain.datasource.storage.SecureStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun provideBaseUrl(): String = "https://newsapi.org/"

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


    @Provides
    @Singleton
    fun provideClient(secureStorage: SecureStorage): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
//        .addInterceptor(Interceptor { chain ->
//            val builder = chain.request().newBuilder()
//            if (secureStorage.getToke().isNotEmpty()) {
//                builder.addHeader(
//                    AUTHORIZATION,
//                    "$BEARER ${secureStorage.getToke()}"
//                )
//            }
 //           chain.proceed(builder.build())
 //       })
        .build()

}