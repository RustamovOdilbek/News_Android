//package com.innovation.news
//
//import com.innovation.news.data.mapper.Mapper
//import kotlinx.coroutines.suspendCancellableCoroutine
//import okhttp3.ResponseBody
//import org.json.JSONException
//import org.json.JSONObject
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import java.net.SocketException
//import java.net.UnknownHostException
//import javax.inject.Inject
//import javax.net.ssl.SSLException
//import javax.net.ssl.SSLHandshakeException
//import kotlin.coroutines.resume
//import kotlin.coroutines.resumeWithException
//import kotlin.coroutines.suspendCoroutine
//@DslMarker
//annotation class WrapperDsl
//
//suspend fun <T> Call<T>.await(): T = suspendCancellableCoroutine {
//    enqueue(object : Callback<T> {
//        override fun onResponse(call: Call<T>, response: Response<T>) {
//            if (response.isSuccessful) {
//                it.resume(response.body()!!)
//            } else {
//                it.resumeWithException(Throwable("Error response"))
//            }
//        }
//
//        override fun onFailure(call: Call<T>, t: Throwable) {
//            it.resumeWithException(t)
//        }
//    })
//}
//
//suspend fun <T> Response<T>.await(): T = suspendCancellableCoroutine {
//    try {
//        val body: T? = body()
//        when (code()) {
//            in 200..299 -> {
//                if (body != null) it.resume(body)
//                else it.resumeWithException(NullPointerException("Response data must not be null"))
//            }
//            500 -> {
//                it.resumeWithException(InternalServerError(""))
//            }
//            401 -> {
//                it.resumeWithException(TokenWrongException(""))
//            }
//            else -> {
//                val message: String = handleError(errorBody())
//                Error(NetworkException(message))
//            }
//        }
//    } catch (e: Throwable) {
//        it.resumeWithException(e)
//    }
//}
//
//suspend fun <T> Response<BaseResponse<T>>.await2(): T = suspendCoroutine {
//    try {
//        val body: BaseResponse<T>? = body()
//        when (code()) {
//            in 200..299 -> {
//                if (body?.data != null) it.resume(body.data!!)
//                else it.resumeWithException(NullPointerException("Response data must not be null"))
//            }
//            500 -> {
//                it.resumeWithException(InternalServerError(""))
//            }
//            401 -> {
//                it.resumeWithException(TokenWrongException(""))
//            }
//            else -> {
//                val message: String = handleError(errorBody())
//                Error(NetworkException(message))
//            }
//        }
//    } catch (e: Throwable) {
//        it.resumeWithException(e)
//    }
//}
//
//private fun handleError(body: ResponseBody?): String {
//    val tempError = """{ "errorMessage" = "Some Error from network" }"""
//    val byteArray: ByteArray = body?.bytes() ?: tempError.toByteArray()
//    return try {
//        JSONObject(String(byteArray)).getString("errorMessage")
//    } catch (e: JSONException) {
//        e.message.toString()
//    }
//}
//
//class ResponseWrapper @Inject constructor() {
//
//    suspend fun <T, E> wrapperListX(
//        mapper: Mapper<T, E>,
//        body: suspend () -> Response<BaseResponse<List<E>>>
//    ): ResourceUI<List<T>> {
//        return try {
//            val response: Response<BaseResponse<List<E>>> = body()
//            checkStatusList(mapper, response)
//        } catch (e: Exception) {
//            Timber.d("eee: wrapperListX $e")
//            when (e) {
//                is NetworkException -> Error(Exception("Data is Empty"))
//                is NullPointerException -> Error(Exception("Data is Empty"))
//                is UnknownHostException -> Error(ServerException("Not connection with server"))
//                is SSLHandshakeException -> Error(ServerException("Not connection with server"))
//                is SSLException -> Error(ServerException("Not connection with server"))
//                is SocketException -> Error(SocketException("Not connection with server"))
//                else -> Error(e)
//            }
//        }
//    }
//
//    private fun <T, E> checkStatusList(
//        mapper: Mapper<T, E>,
//        response: Response<BaseResponse<List<E>>>
//    ): ResourceUI<List<T>> {
//        val body: BaseResponse<List<E>>? = response.body()
//        val data: List<T>? = body?.data?.let { list -> list.map { mapper.mapData(it) } }
//        return when (response.code()) {
//            in 200..299 -> {
//                if (data != null) ResourceUI.Resource(data, response.code())
//                else Error(NullPointerException("Response data must not be null"))
//            }
//            500 -> {
//                Error(InternalServerError("InternalServerError"))
//            }
//            401 -> {
//                Error(TokenWrongException(""))
//            }
//            //451 - majburiy uopdate
//            //418 - blok user
//            428 -> {
//                val message: String = handleError(response.errorBody())
//                Error(GlobalException(message, response.code()))
//            }
//            451 -> {
//                val message: String = handleError(response.errorBody())
//                Error(AppUpdateException(message))
//            }
//            418 -> {
//                val message: String = handleError(response.errorBody())
//                Error(BlockUserException(message))
//            }
//            422 -> {
//                val message: String = handleError(response.errorBody())
//                Error(InfoForUser(message))
//            }
//            else -> {
//                val message: String = handleError(response.errorBody())
//                Error(NetworkException(message))
//            }
//        }
//    }
//
//    suspend fun <T, E> wrapperX(
//        mapper: Mapper<T, E>,
//        body: suspend () -> Response<BaseResponse<E>>
//    ): ResourceUI<T> {
//        return try {
//            val response: Response<BaseResponse<E>> = body()
//            checkStatus(mapper, response)
//        } catch (e: Exception) {
//            Timber.d("eee: wrapperX 1 $e")
//            when (e) {
//                is NetworkException -> Error(Exception("Data is Empty"))
//                is NullPointerException -> Error(Exception("Data is Empty"))
//                is UnknownHostException -> Error(ServerException("Not connection with server"))
//                is SSLHandshakeException -> Error(ServerException("Not connection with server"))
//                is SSLException -> Error(ServerException("Not connection with server"))
//                is SocketException -> Error(SocketException("Not connection with server"))
//                else -> Error(e)
//            }
//        }
//    }
//
//    private fun <T, E> checkStatus(
//        mapper: Mapper<T, E>,
//        response: Response<BaseResponse<E>>
//    ): ResourceUI<T> {
//        val body: BaseResponse<E>? = response.body()
//        val data: T? = body?.data?.let { mapper.mapData(it) }
//        return when (response.code()) {
//            in 200..299 -> {
//                if (data != null) ResourceUI.Resource(data, response.code())
//                else Error(NullPointerException("Response data must not be null"))
//            }
//            500 -> {
//                Error(InternalServerError("InternalServerError"))
//            }
//            401 -> {
//                Error(TokenWrongException(""))
//            }
//            428 -> {
//                val message: String = handleError(response.errorBody())
//                Error(GlobalException(message, response.code()))
//            }
//            451 -> {
//                val message: String = handleError(response.errorBody())
//                Error(AppUpdateException(message))
//            }
//            418 -> {
//                val message: String = handleError(response.errorBody())
//                Error(BlockUserException(message))
//            }
//            422 -> {
//                val message: String = handleError(response.errorBody())
//                Error(InfoForUser(message))
//            }
//            else -> {
//                val message: String = handleError(response.errorBody())
//                Error(NetworkException(message))
//            }
//        }
//    }
//
//    suspend fun <T, E> wrapperX(
//        mapper: (dto: E) -> T,
//        body: suspend () -> Response<BaseResponse<E>>
//    ): ResourceUI<T> {
//        Timber.d("eee: wrapperX 2 mapper ${mapper.toString()} ")
//        return try {
//            val response: Response<BaseResponse<E>> = body()
//            Timber.d("eee: wrapperX 2 response ${response.toString()}")
//            Timber.d("eee: wrapperX 2 response ${response.body()}")
//            checkStatus(mapper, response)
//        } catch (e: Exception) {
//            Timber.d("eee: wrapperX 2 $e")
//            when (e) {
//                is NetworkException -> Error(Exception("Data is Empty"))
//                is NullPointerException -> Error(Exception("Data is Empty"))
//                is UnknownHostException -> Error(ServerException("Not connection with server"))
//                is SSLHandshakeException -> Error(ServerException("Not connection with server"))
//                is SSLException -> Error(ServerException("Not connection with server"))
//                is SocketException -> Error(SocketException("Not connection with server"))
//                else -> Error(e)
//            }
//        }
//    }
//
//    private fun <T, E> checkStatus(
//        mapper: (dto: E) -> T,
//        response: Response<BaseResponse<E>>
//    ): ResourceUI<T> {
//        Timber.d("eee: wrapperX 2 code ${response.code()}")
//        val body: BaseResponse<E>? = response.body()
//        Timber.d("eee: wrapperX 2 body $body")
//        val data: T? = body?.data?.let { mapper.invoke(it) }
//        Timber.d("eee: wrapperX 2 data $data")
//        Timber.d("eee: wrapperX 22 code ${response.code()}")
//        return when (response.code()) {
//            in 200..299 -> {
//                Timber.d("eee: wrapperX 23 code ${response.code()}")
//                if (data != null) ResourceUI.Resource(data, response.code())
//                else Error(NullPointerException("Response data must not be null"))
//            }
//            500 -> {
//                Error(InternalServerError("InternalServerError"))
//            }
//            401 -> {
//                Error(TokenWrongException(""))
//            }
//            428 -> {
//                val message: String = handleError(response.errorBody())
//                Error(GlobalException(message, response.code()))
//            }
//            451 -> {
//                val message: String = handleError(response.errorBody())
//                Error(AppUpdateException(message))
//            }
//            418 -> {
//                val message: String = handleError(response.errorBody())
//                Error(BlockUserException(message))
//            }
//            422 -> {
//                val message: String = handleError(response.errorBody())
//                Error(InfoForUser(message))
//            }
//            else -> {
//                val message: String = handleError(response.errorBody())
//                Error(NetworkException(message))
//            }
//        }
//    }
//
//    private fun handleError(body: ResponseBody?): String {
//        val tempError = """{ "errorMessage" = "Some Error from network" }"""
//        val byteArray: ByteArray = body?.bytes() ?: tempError.toByteArray()
//        return try {
//            JSONObject(String(byteArray)).getString("errorMessage")
//        } catch (e: JSONException) {
//            e.message.toString()
//        }
//    }
//}