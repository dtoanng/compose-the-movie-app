package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.mapper

import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.local.genre.GenreEntity
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.local.movie.MovieEntity
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.remote.response.movie.MovieDto
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.domain.model.Genre
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.domain.model.Movie

fun MovieDto.toMovieEntity(
    category: String
) = MovieEntity(
    id = id,
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage,
    releaseDate = releaseDate,
    overview = overview,
    voteCount = voteCount,
    backdropPath = backdropPath,
    popularity = popularity,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    adult = adult,
    video = video,
    genreIds = try {
        genreIds?.joinToString(",") ?: "-1,-2"
    } catch (e: Exception) {
        "-1,-2"
    },
    category = category
)

fun MovieEntity.toMovie(
    category: String
) = Movie(
    id = id,
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage,
    releaseDate = releaseDate,
    overview = overview,
    voteCount = voteCount,
    backdropPath = backdropPath,
    popularity = popularity,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    adult = adult,
    video = video,
    category = category,
    genreIds = try {
        genreIds.split(",").map { it.toInt() }
    } catch (e: Exception) {
        listOf(-1, -2)
    }
)

fun MovieDto.toMovie() = Movie(
    id = id,
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage,
    releaseDate = releaseDate,
    overview = overview,
    voteCount = voteCount,
    backdropPath = backdropPath,
    popularity = popularity,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    adult = adult,
    video = video,
    genreIds = genreIds,
    category = ""
)

fun GenreEntity.toGenre() = Genre(id = id, name = name)