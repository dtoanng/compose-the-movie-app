package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.movieList.util

sealed class Screen(val rout: String) {
    object Home : Screen("main")
    object PopularMovieList : Screen("popularMovie")
    object UpcomingMovieList : Screen("upcomingMovie")
    object Details : Screen("details")
}