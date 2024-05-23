package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.local.genre.GenreEntity
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.local.movie.MovieEntity

@Database(
    entities = [MovieEntity::class, GenreEntity::class],
    version = 2
)
abstract class MovieDatabase : RoomDatabase() {
    abstract val dao: MovieDao
}