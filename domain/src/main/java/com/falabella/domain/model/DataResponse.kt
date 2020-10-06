package com.falabella.domain.model

sealed class DataResponse<out T: Any> {
    data class Success<out  T: Any>(val data : T) : DataResponse<T>()
    data class ServerError(val errorCode : Int) : DataResponse<Nothing>()
    object ConnectionError : DataResponse<Nothing>()
}
