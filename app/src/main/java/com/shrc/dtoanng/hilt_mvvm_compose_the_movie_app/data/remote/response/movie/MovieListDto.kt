package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.remote.response.movie

import com.google.gson.annotations.SerializedName
data class MovieListDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)