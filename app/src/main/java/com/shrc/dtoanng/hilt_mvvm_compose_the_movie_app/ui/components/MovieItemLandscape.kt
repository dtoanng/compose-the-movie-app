package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
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

@Composable
fun MovieItemLandscape(
    movie: Movie,
    navHostController: NavHostController
) {
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data = MovieAPI.IMAGE_BASE_URL + movie.posterPath)
            .size(Size.ORIGINAL)
            .build()
    ).state

    if (imageState is AsyncImagePainter.State.Success) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(5.dp)),
            painter = imageState.painter,
            contentDescription = movie.title,
            contentScale = ContentScale.Inside
        )
    }
}