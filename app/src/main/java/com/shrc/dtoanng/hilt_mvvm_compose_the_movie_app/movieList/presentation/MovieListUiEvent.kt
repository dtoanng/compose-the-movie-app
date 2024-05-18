package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.movieList.presentation

sealed interface MovieListUiEvent {
    data class Paginate(val category: String) : MovieListUiEvent
    data object Navigate : MovieListUiEvent
}