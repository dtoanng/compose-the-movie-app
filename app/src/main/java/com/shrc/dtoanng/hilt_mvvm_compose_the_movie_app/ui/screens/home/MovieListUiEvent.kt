package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home

sealed interface MovieListUiEvent {
    data class Paginate(val category: String) : MovieListUiEvent
    data object Navigate : MovieListUiEvent
}