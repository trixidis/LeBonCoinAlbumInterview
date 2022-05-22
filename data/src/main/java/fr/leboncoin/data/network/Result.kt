package fr.leboncoin.data.network

sealed class Result<T> {
    data class Progress<T>(val data: T? = null) : Result<T>()
    data class Success<T>(val data: T) : Result<T>()
    data class Failure<T>(val e: Throwable) : Result<T>()
}