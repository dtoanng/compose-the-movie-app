package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.local.movie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MovieDao {
    @Upsert
    suspend fun upsertMovieList(movies: List<MovieEntity>)

    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    suspend fun getMovieByMovieId(id: Int): MovieEntity?
    @Query("SELECT * FROM MovieEntity WHERE category = :category")
    suspend fun getMovieListByMovieCategory(category: String): List<MovieEntity>
}