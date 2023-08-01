package com.innovation.news.di

import android.content.Context
import androidx.room.Room
import com.innovation.news.data.db.AppDatabase
import com.innovation.news.data.db.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTaskRoomDB(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "news.db"
        ).fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun provideTaskDao(appDatabase: AppDatabase): NewsDao {
        return appDatabase.createNewsDao()
    }
}