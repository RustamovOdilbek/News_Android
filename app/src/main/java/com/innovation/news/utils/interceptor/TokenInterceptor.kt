package com.innovation.news.utils.interceptor

import android.content.Context
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHeaders
import com.google.gson.Gson
import com.innovation.news.utils.SharedPref
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton


//@Singleton
//class TokenInterceptor @Inject constructor(
//    private val sharedPref: SharedPref,
//    @ApplicationContext private val context: Context,
//) : Interceptor {
//    @Throws(Exception::class)
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val oldRequest: Request = newRequestWithAccessToken(chain.request(), sharedPref.accessToken.toString())
//        var oldResponse = chain.proceed(oldRequest)
//        if (oldResponse.code == HttpURLConnection.HTTP_UNAUTHORIZED){
//            val client = OkHttpClient()
//            val params = JSONObject()
//            synchronized(this){
//                params.put("refresh_token", sharedPref.refreshToken ?: "")
//                val body: RequestBody = RequestBody.create(oldResponse.body?.contentType(),params.toString())
//                val nRequest = Request.Builder()
//                    .url("${BASE_URL}/api/${getLanguage(context)}/refresh/token")
//                    .post(body)
//                    .build()
//
//                val responseRefresh = client.newCall(nRequest).execute()
//                if (responseRefresh.code == HttpURLConnection.HTTP_OK){
//                    val jsonData = responseRefresh.body?.string() ?: ""
//                    val gson = Gson()
//                    val resAuth: ResAuthModel = gson.fromJson(jsonData, ResAuthModel::class.java)
//                    sharedPref.accessToken = resAuth.access_token
//                    sharedPref.refreshToken = resAuth.refresh_token
//                    oldResponse.close()
//                    return chain.proceed(newRequestWithAccessToken(oldRequest,sharedPref.accessToken.toString()))
//                } else {
//                    sharedPref.clearAll()
//                }
//            }
//        }
//        return oldResponse
//    }
//
//
//    private fun newRequestWithAccessToken(request: Request, accessToken: String): Request {
//        return request.newBuilder()
//            .header(HttpHeaders.AUTHORIZATION, "Bearer $accessToken")
//            .header(HttpHeaders.ACCEPT,APPLICATION_JSON)
//            .header(HttpHeaders.CONTENT_TYPE,APPLICATION_JSON)
//            .build()
//    }
//}
