package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.repository

import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.local.movie.MovieDatabase
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.mapper.toMovie
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.mapper.toMovieEntity
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.remote.MovieAPI
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.domain.model.Movie
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.domain.repository.MovieListRepository
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(
    private val movieApi: MovieAPI,
    private val movieDatabase: MovieDatabase
) :
    MovieListRepository {
    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading(true))

            val localMovieList = movieDatabase.dao.getMovieListByMovieCategory(category)

            val shouldLoadLocalMovie = localMovieList.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalMovie) {
                emit(Resource.Success(localMovieList.map { it.toMovie(category) }))

                emit(Resource.Loading(false))

                return@flow
            }

            val movieListFromApi = try {
                movieApi.getMovieList(category, page)
            } catch (exception: IOException) {
                emit(Resource.Error(exception.message ?: "Error while loading data..."))
                return@flow
            } catch (exception: HttpException) {
                emit(Resource.Error(exception.message ?: "Error while loading data..."))
                return@flow
            } catch (exception: Exception) {
                emit(Resource.Error(exception.message ?: "Error while loading data..."))
                return@flow
            }

            val movieEntities = movieListFromApi.results.let {
                it.map { movie ->
                    movie.toMovieEntity(category)
                }
            }

            movieDatabase.dao.upsertMovieList(movieEntities)
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getMovie(id: Int): Flow<Resource<Movie>> {
        return flow {
            emit(Resource.Loading(true))

            val movieEntity = movieDatabase.dao.getMovieByMovieId(id)

            if (movieEntity != null) {
                emit(Resource.Loading(false))
                emit(Resource.Success(movieEntity.toMovie(movieEntity.category)))
                return@flow
            }

            emit(Resource.Error("Movie not found..."))
            emit(Resource.Loading(false))
        }
    }
}