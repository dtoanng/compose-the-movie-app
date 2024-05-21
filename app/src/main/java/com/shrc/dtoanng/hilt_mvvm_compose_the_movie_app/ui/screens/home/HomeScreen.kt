package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.Upcoming
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.R
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.navigation.Screen
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home.popular.PopularMoviesScreen
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.screens.home.upcoming.UpcomingMoviesScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
//    val homeViewModel = hiltViewModel<HomeViewModel>()
//    val movieState = homeViewModel.movieListState.collectAsState().value

    val bottomNavController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "The MovieCompose",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    )
                },
                modifier = Modifier.shadow(2.dp),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    MaterialTheme.colorScheme.inverseOnSurface,
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(
                bottomNavController = bottomNavController,
                //onEvent = homeViewModel::onEvent
            )
        }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            NavHost(
                navController = bottomNavController,
                startDestination = Screen.PopularMovieList.rout
            ) {
                composable(Screen.PopularMovieList.rout) {
                    PopularMoviesScreen(
                        navController = navController
                    )
                }
                composable(Screen.UpcomingMovieList.rout) {
                    UpcomingMoviesScreen(
                        navHostController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    bottomNavController: NavHostController,
    //onEvent: (MovieListUiEvent) -> Unit
) {

    val items = listOf(
        BottomItem(title = stringResource(R.string.home), icon = Icons.Rounded.Home),
        BottomItem(title = stringResource(R.string.popular), icon = Icons.Rounded.Movie),
        BottomItem(title = stringResource(R.string.top_rated), icon = Icons.Rounded.Star),
        BottomItem(title = stringResource(R.string.upcoming), icon = Icons.Rounded.Upcoming),
    )

    val selected = rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface),
        ) {
            items.forEachIndexed { index, bottomItem ->
                NavigationBarItem(
                    selected = selected.intValue == index,
                    onClick = {
                        selected.intValue = index
                        when (selected.intValue) {
                            0 -> {
                                //onEvent(MovieListUiEvent.Navigate)
                                bottomNavController.apply {
                                    popBackStack()
                                    navigate(Screen.PopularMovieList.rout)
                                }
                            }

                            1 -> {
                                //onEvent(MovieListUiEvent.Navigate)
                                bottomNavController.apply {
                                    popBackStack()
                                    navigate(Screen.PopularMovieList.rout)
                                }
                            }

                            2 -> {
                                //onEvent(MovieListUiEvent.Navigate)
                                bottomNavController.apply {
                                    popBackStack()
                                    navigate(Screen.UpcomingMovieList.rout)
                                }
                            }

                            3 -> {
                                //onEvent(MovieListUiEvent.Navigate)
                                bottomNavController.apply {
                                    popBackStack()
                                    navigate(Screen.UpcomingMovieList.rout)
                                }
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = bottomItem.icon,
                            contentDescription = bottomItem.title,
                            tint = MaterialTheme.colorScheme.onBackground,
                        )
                    },
                    label = {
                        Text(
                            text = bottomItem.title,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                )
            }
        }
    }
}

data class BottomItem(val title: String, val icon: ImageVector)