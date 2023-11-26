package com.example.newsapp.other

sealed class ResponseState<out T> {

    object Loading : ResponseState<Nothing>()
    data class Success<T>(val data: T) : ResponseState<T>()
    data class Error(val throwable: Throwable) : ResponseState<Nothing>()
}