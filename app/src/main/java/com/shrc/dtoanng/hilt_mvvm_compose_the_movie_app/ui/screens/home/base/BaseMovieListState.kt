package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home.base

import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.domain.model.Movie

data class BaseMovieListState(
    val isLoading: Boolean = false,
    val currentPage: Int = 1,
    val isCurrentScreen: Boolean = true,
    val movieList: List<Movie> = emptyList()
)