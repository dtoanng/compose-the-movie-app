package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.details.presentation.components

import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.movieList.domain.model.Movie

data class MovieDetailsState (
    val isLoading: Boolean = false,
    val movie: Movie? = null
)