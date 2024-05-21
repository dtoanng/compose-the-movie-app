package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home.base

sealed interface BaseMovieListUiEvent {
    data class Paginate(val category: String) : BaseMovieListUiEvent
    data object Navigate : BaseMovieListUiEvent
}