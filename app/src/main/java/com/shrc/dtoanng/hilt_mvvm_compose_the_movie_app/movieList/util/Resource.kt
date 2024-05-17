package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.movieList.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(null)
    class Loading<T>(val loading: Boolean = true) : Resource<T>(null)
}