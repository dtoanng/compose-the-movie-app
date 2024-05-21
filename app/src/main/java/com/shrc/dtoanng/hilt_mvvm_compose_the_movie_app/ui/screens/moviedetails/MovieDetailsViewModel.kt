package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.moviedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.domain.repository.MovieRepository
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle
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

            movieRepository.getMovie(movieId).collect { result ->
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