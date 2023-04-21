package com.samm.ktor01.core

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val message: String) : Resource<T>()
    class Loading<T>(isLoading: Boolean = false) : Resource<T>()
}
