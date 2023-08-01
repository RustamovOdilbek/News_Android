package com.innovation.news.data.source.storage.impl

import com.innovation.news.common.SharedPref
import com.innovation.news.domain.datasource.storage.SecureStorage
import javax.inject.Inject

class SecureStorageImpl @Inject constructor(private val sharedPref: SharedPref) : SecureStorage {
}