package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.movieList.domain.model

class Movie(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>?,
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
    val id: Int,
    val category: String
)