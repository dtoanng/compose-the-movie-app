package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.moviedetails

import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.domain.model.Movie

data class MovieDetailsState (
    val isLoading: Boolean = false,
    val movie: Movie? = null
)