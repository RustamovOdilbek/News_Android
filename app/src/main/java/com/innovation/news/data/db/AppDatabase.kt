package com.innovation.news.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.innovation.news.data.models.entity.NewsEntity

@Database(entities = [NewsEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun createNewsDao(): NewsDao

}