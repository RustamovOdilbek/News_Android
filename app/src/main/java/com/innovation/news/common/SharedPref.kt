package com.innovation.news.common

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPref @Inject constructor(@ApplicationContext val context: Context) {

    private val pref = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)

//    var token: String
//        get() = pref.getString(TOKEN, EMPTY_STRING)!!
//        set(value) = pref.edit { this.putString(TOKEN, value) }
}