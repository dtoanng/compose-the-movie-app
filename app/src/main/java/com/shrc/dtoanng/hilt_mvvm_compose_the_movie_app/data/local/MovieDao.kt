package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.local.genre.GenreEntity
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.local.movie.MovieEntity

@Dao
interface MovieDao {
    @Upsert
    suspend fun upsertMovieList(movies: List<MovieEntity>)

    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    suspend fun getMovieByMovieId(id: Int): MovieEntity?
    @Query("SELECT * FROM MovieEntity WHERE category = :category")
    suspend fun getMovieListByMovieCategory(category: String): List<MovieEntity>

    @Upsert
    suspend fun upsertGenresList(genres: List<GenreEntity>)

    @Query("SELECT * FROM GenreEntity")
    suspend fun getGenreByGenresList(): List<GenreEntity>
}