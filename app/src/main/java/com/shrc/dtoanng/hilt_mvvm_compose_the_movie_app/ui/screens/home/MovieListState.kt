package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home

import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.domain.model.Movie

data class MovieListState(
    val isLoading: Boolean = false,
    val popularMovieListPage: Int = 1,
    val upcomingMovieListPage: Int = 1,
    val isCurrentPopularScreen: Boolean = true,
    val popularMovieList: List<Movie> = emptyList(),
    val upcomingMovieList: List<Movie> = emptyList()
)