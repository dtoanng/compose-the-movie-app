package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel: ViewModel() {

    protected val _movieListState = MutableStateFlow(BaseMovieListState())
    val movieListState = _movieListState.asStateFlow()

    fun onEvent(event: BaseMovieListUiEvent) {
        when (event) {
            BaseMovieListUiEvent.Navigate -> {
                //todo: implement later
            }

            is BaseMovieListUiEvent.Paginate -> {
                doPagingTask()
            }
        }
    }

    abstract fun doPagingTask()

}