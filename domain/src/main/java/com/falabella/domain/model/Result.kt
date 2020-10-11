package com.falabella.domain.model

sealed class Result<out T: Any> {
    data class Success<out  T: Any>(val data : T) : Result<T>()
    data class ServerError(val errorCode : Int) : Result<Nothing>()
    object ConnectionError : Result<Nothing>()
}
