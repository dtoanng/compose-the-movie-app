package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.movieList.data.local.movie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MovieDao {
    @Upsert
    suspend fun upsertMovieList(movies: List<MovieEntity>)

    @Query("SELECT * FROM MovieEntity WHERE movieId = :movieId")
    suspend fun getMovieByMovieId(movieId: Int): MovieEntity?
    @Query("SELECT * FROM MovieEntity WHERE movieCategory = :category")
    suspend fun getMovieListByMovieCategory(category: String): List<MovieEntity>
}