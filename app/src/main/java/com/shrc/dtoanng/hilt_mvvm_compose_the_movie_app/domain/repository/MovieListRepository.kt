package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.domain.repository

import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.domain.model.Movie
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.utils.Resource
import kotlinx.coroutines.flow.Flow
interface MovieListRepository {
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>>

    suspend fun getMovie(id: Int): Flow<Resource<Movie>>
}