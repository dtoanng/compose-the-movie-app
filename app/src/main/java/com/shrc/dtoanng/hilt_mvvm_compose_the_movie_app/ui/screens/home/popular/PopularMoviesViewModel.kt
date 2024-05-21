package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home.popular

import androidx.lifecycle.viewModelScope
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.domain.repository.MovieRepository
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home.base.BaseViewModel
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.utils.Category
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    init {
        getPopularMovie(false)
    }

    override fun doPagingTask() {
        getPopularMovie(true)
    }

    private fun getPopularMovie(forceFetchFromRemote: Boolean) {
        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true)
            }

            movieRepository.getMovieList(
                forceFetchFromRemote = forceFetchFromRemote,
                category = Category.POPULAR,
                page = _movieListState.value.currentPage
            ).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _movieListState.update {
                            it.copy(isLoading = false)
                        }
                    }

                    is Resource.Loading -> {
                        _movieListState.update {
                            it.copy(isLoading = result.loading)
                        }
                    }

                    is Resource.Success -> {
                        result.data?.let { popularMovieList ->
                            _movieListState.update {
                                it.copy(
                                    movieList = movieListState.value.movieList + popularMovieList.shuffled(),
                                    currentPage = movieListState.value.currentPage + 1
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}