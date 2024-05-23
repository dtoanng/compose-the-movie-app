package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.remote.response.genre

import com.google.gson.annotations.SerializedName

data class GenresListDto(
    @SerializedName("genres")
    val genres: List<GenreDto>
)