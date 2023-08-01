package com.innovation.news.data.source.local.impl

import com.innovation.news.data.db.NewsDao
import com.innovation.news.domain.datasource.local.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val newsDao: NewsDao): LocalDataSource {
}