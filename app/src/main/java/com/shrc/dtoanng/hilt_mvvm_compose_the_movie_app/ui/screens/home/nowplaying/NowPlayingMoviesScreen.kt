package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home.nowplaying

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.components.MovieItem
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home.base.BaseMovieListUiEvent
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.utils.Category

@Composable
fun NowPlayingMoviesScreen(navController: NavHostController) {
    val nowPlayingMoviesViewModel = hiltViewModel<NowPlayingMoviesViewModel>()
    val movieListState = nowPlayingMoviesViewModel.movieListState.collectAsState().value

    if (movieListState.movieList.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxHeight()
                .background(Color(0xffECEFF1))
        ) {
            items(movieListState.movieList.size) { index ->
                MovieItem(movie = movieListState.movieList[index], navHostController = navController)
                if (index >= movieListState.movieList.size - 1 && !movieListState.isLoading) {
                    nowPlayingMoviesViewModel.onEvent(BaseMovieListUiEvent.Paginate(Category.NOW_PLAYING))
                }
            }
        }

    }
}