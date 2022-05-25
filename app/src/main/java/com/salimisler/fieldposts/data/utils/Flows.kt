package com.salimisler.fieldposts.data.utils

import com.salimisler.fieldposts.core.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response

inline fun <T> networkRequest(
   crossinline call: suspend () -> Response<T>
): Flow<Resource<T>> {
    return flow<Resource<T>> {
        val response = call.invoke()
        if (response.isSuccessful) {
            emit(Resource.success(response.body()))
        } else {
            emit(Resource.error(response.message()))
        }
    }.onStart {
        emit(Resource.loading())
    }.catch {
        emit(Resource.error(it.localizedMessage))
    }.flowOn(Dispatchers.IO)
}