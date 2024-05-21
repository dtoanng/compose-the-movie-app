package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.navigation

sealed class Screen(val rout: String) {
    data object Home : Screen("main")
    data object PopularMovieList : Screen("popularMovie")
    data object UpcomingMovieList : Screen("upcomingMovie")
    data object Details : Screen("details")
}