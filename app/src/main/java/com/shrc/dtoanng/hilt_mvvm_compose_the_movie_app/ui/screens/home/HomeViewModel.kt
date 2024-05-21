package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home

import androidx.lifecycle.ViewModel
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.domain.repository.MovieRepository
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home.base.BaseViewModel
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.utils.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel() {
    override fun doPagingTask() {

    }
}