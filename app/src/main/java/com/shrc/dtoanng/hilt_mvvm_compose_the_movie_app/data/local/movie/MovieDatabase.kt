package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.local.movie

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {
    abstract val dao: MovieDao
}