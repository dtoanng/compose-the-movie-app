package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.movieList.domain.repository

import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.movieList.domain.model.Movie
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.movieList.util.Resource
import kotlinx.coroutines.flow.Flow
interface MovieListRepository {
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>>

    suspend fun getMovie(id: Int): Flow<Resource<Movie>>
}