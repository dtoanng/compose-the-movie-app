package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home.programs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.domain.model.Genre
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.domain.repository.MovieRepository
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home.base.BaseMovieListState
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.utils.Configuration
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProgramsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _genresListState = MutableStateFlow(GenresListState())
    val genresListState = _genresListState.asStateFlow()

    private val _selectedGenre = MutableStateFlow(Genre(null, Configuration.DEFAULT_GENRE_ITEM))
    val selectedGenre = _selectedGenre.asStateFlow()

    private val _movieListState = MutableStateFlow(BaseMovieListState())
    val movieListState = _movieListState.asStateFlow()

    fun getGenresList(forceFetchFromRemote: Boolean) {
        viewModelScope.launch {
            _genresListState.update {
                it.copy(isLoading = true)
            }

            movieRepository.getGenresList(forceFetchFromRemote = forceFetchFromRemote).collectLatest { result ->

                when (result) {
                    is Resource.Error -> {
                        _genresListState.update {
                            it.copy(isLoading = false)
                        }
                    }

                    is Resource.Loading -> {
                        _genresListState.update {
                            it.copy(isLoading = result.loading)
                        }
                    }

                    is Resource.Success -> {
                        Timber.d("Genres List State: ${result.data}")
                        result.data?.let { genresList ->
                            _genresListState.update {
                                it.copy(
                                    isLoading = false,
                                    genresList = genresList.toMutableList()
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    fun setSelectedGenre(genre: Genre) {
        _selectedGenre.value = genre
        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true)
            }

            movieRepository.getDiscoverMovie(forceFetchFromRemote = true, genre = genre.id.toString(), 1).collectLatest {
                result -> when (result) {
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
                    Timber.d("setSelectedGenre/Movie list: ${result.data}")
                    result.data?.let { movieList ->
                        _movieListState.update {
                            it.copy(
                                movieList = movieList,
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