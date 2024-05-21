package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.local.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MovieEntity(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,

    @PrimaryKey
    val id: Int,
    val category: String
)