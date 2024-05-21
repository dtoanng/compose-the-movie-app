package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home.popular

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.utils.Category
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.components.MovieItem
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home.base.BaseMovieListUiEvent

@Composable
fun PopularMoviesScreen(navController: NavHostController) {

    val popularMoviesViewModel = hiltViewModel<PopularMoviesViewModel>()
    val movieListState = popularMoviesViewModel.movieListState.collectAsState().value

    if (movieListState.movieList.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
        ) {
            items(movieListState.movieList.size) { index ->
                MovieItem(
                    movie = movieListState.movieList[index],
                    navHostController = navController
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (index >= movieListState.movieList.size - 1 && !movieListState.isLoading) {
                    popularMoviesViewModel.onEvent(BaseMovieListUiEvent.Paginate(Category.POPULAR))
                }
            }
        }
    }

}