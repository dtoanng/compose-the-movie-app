package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.remote.MovieAPI
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.domain.model.Movie
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.navigation.Screen

@Composable
fun MovieItemPoster(
    movie: Movie,
    navHostController: NavHostController
) {
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data = MovieAPI.IMAGE_BASE_URL + movie.posterPath)
            .size(Size.ORIGINAL)
            .build()
    ).state

    if (imageState is AsyncImagePainter.State.Error) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.ImageNotSupported,
                contentDescription = movie.title
            )
        }
    }

    if (imageState is AsyncImagePainter.State.Success) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .wrapContentHeight()
                .clip(RoundedCornerShape(5.dp))
                .clickable {
                    navHostController.navigate(Screen.Details.rout + "/${movie.id}")
                },
            painter = imageState.painter,
            contentDescription = movie.title,
            contentScale = ContentScale.Inside,
        )
    }
}