package com.sudo.domain.common

import java.lang.Exception

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: String) : Result<Nothing>()
    object Loading : Result<Nothing>()
}
//
//fun Result<*>.isSuccess() = this is Result.Success && this.data != null