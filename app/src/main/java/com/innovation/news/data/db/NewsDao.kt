package com.innovation.news.data.db

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.innovation.news.common.Constants.TABLE_NAME
import com.innovation.news.data.models.entity.NewsEntity

@Dao
interface NewsDao {

    suspend fun insertNewsListLocal(newsEntityList: List<NewsEntity>){
        newsEntityList.forEach {
            insertNewsIfNotExists(it)
        }
    }

    @Transaction
    suspend fun insertNewsIfNotExists(newsEntity: NewsEntity) {
        val existingNewsCount = countNewsWithTitle(newsEntity.title)
        if (existingNewsCount == 0) {
            Log.d("Dasdsdasdsda", "insertNewsIfNotExists: ${newsEntity}")
            insertNews(newsEntity)
        }
    }

    @Query("SELECT COUNT(*) FROM $TABLE_NAME WHERE title = :titleToCheck")
    suspend fun countNewsWithTitle(titleToCheck: String): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(newsEntity: NewsEntity)

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getAllNewsLocal(): List<NewsEntity>

    @Update
    suspend fun updateNewsLocal(newsEntity: NewsEntity)

    @Query("SELECT * FROM $TABLE_NAME WHERE isSaved = 1")
    suspend fun getSavedNewsList(): List<NewsEntity>

}