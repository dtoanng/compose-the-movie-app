package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home.programs

import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.domain.model.Genre

data class GenresListState(
    val isLoading: Boolean = false,
    val genresList: MutableList<Genre> = mutableListOf()
)
