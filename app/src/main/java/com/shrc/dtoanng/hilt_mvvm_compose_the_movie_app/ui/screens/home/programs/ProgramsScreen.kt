package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home.programs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.R
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.domain.model.Genre
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.components.MovieItem
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.components.MovieItemPoster
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.components.SelectableGenreChip
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home.base.BaseMovieListUiEvent
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home.nowplaying.NowPlayingMoviesViewModel
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home.popular.PopularMoviesViewModel
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.utils.Category
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.utils.Configuration
import timber.log.Timber

@Composable
fun ProgramsScreen(navController: NavHostController) {

    val programsViewModel = hiltViewModel<ProgramsViewModel>()
    val genresListState = programsViewModel.genresListState.collectAsState().value
    val selectedGenre = programsViewModel.selectedGenre.collectAsState().value

    LaunchedEffect(key1 = 0) {
        programsViewModel.getGenresList(false)
    }

    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        item {
            Timber.d("Genres 1: ${genresListState.genresList}")
            if (genresListState.genresList.isNotEmpty()) {
                if (genresListState.genresList.first().name != Configuration.DEFAULT_GENRE_ITEM) {
                    genresListState.genresList.add(0, Genre(null, Configuration.DEFAULT_GENRE_ITEM))
                }
                Timber.d("Genres: ${genresListState.genresList}")
                LazyRow(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp, start = 9.dp, end = 9.dp)
                        .fillMaxWidth()
                ) {
                    items(genresListState.genresList) { item ->
                        SelectableGenreChip(
                            selected = item.name === selectedGenre.name,
                            genre = item.name,
                        ) {
                            programsViewModel.setSelectedGenre(item)
                        }
                    }
                }
            }
        }

        item {
            NowPlayingMovieRow(navController)
        }

        item {
            PopularMovieRow(navController)
        }

        item {
            //todo:
        }

        item {
            //todo:
        }
    }
}

@Composable
private fun PopularMovieRow(navHostController: NavHostController) {
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
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        Text(
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            text = stringResource(R.string.what_popular),
            modifier = Modifier.padding(vertical = 14.dp, horizontal = 8.dp)
        )
        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            modifier = Modifier.height(350.dp),
            contentPadding = PaddingValues(vertical = 1.dp, horizontal = 1.dp)
        ) {
            items(movieListState.movieList.size) { index ->
                MovieItem(
                    movie = movieListState.movieList[index],
                    navHostController = navHostController
                )

                if (index >= movieListState.movieList.size - 1 && !movieListState.isLoading) {
                    popularMoviesViewModel.onEvent(BaseMovieListUiEvent.Paginate(Category.POPULAR))
                }
            }
        }
    }
}

@Composable
private fun NowPlayingMovieRow(navHostController: NavHostController) {
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
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        Text(
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            text = stringResource(R.string.now_playing),
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
        )
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            modifier = Modifier.height(350.dp)
        ) {
            items(movieListState.movieList.size) { index ->

                MovieItemPoster(
                    movie = movieListState.movieList[index],
                    navHostController = navHostController
                )

                if (index >= movieListState.movieList.size - 1 && !movieListState.isLoading) {
                    nowPlayingMoviesViewModel.onEvent(BaseMovieListUiEvent.Paginate(Category.POPULAR))
                }
            }
        }
    }
}