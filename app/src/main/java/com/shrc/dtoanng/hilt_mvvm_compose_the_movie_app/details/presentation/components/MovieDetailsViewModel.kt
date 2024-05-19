package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.details.presentation.components

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.movieList.domain.repository.MovieListRepository
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.movieList.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieListRepository: MovieListRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val movieId = savedStateHandle.get<Int>("movieId")

    private val _detailState = MutableStateFlow(MovieDetailsState())
    val detailState = _detailState.asStateFlow()

    init {
        getMovie(movieId ?: -1)
    }

    private fun getMovie(movieId: Int) {
        viewModelScope.launch {
            _detailState.update {
                it.copy(isLoading = true)
            }

            movieListRepository.getMovie(movieId).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _detailState.update {
                            it.copy(isLoading = result.loading)
                        }
                    }

                    is Resource.Success -> {
                        result.data?.let { movie ->
                            _detailState.update {
                                it.copy(movie = movie)
                            }
                        }
                    }

                    is Resource.Error -> {
                        _detailState.update {
                            it.copy(isLoading = false)
                        }
                    }
                }
            }
        }
    }
}