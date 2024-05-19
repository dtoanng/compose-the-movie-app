package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.details.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.R
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.movieList.data.remote.MovieAPI
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.movieList.util.RatingBar

@Preview(name = "MovieDetailsScreen")
@Composable
fun MovieDetailsScreen() {

    val detailViewModel = hiltViewModel<MovieDetailsViewModel>()
    val detailState = detailViewModel.detailState.collectAsState().value

    val backDropImageState =
        createImageState(imgPath = MovieAPI.IMAGE_BASE_URL + detailState.movie?.backdropPath)
    val posterImageState =
        createImageState(imgPath = MovieAPI.IMAGE_BASE_URL + detailState.movie?.posterPath)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        if (backDropImageState is AsyncImagePainter.State.Error) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.ImageNotSupported,
                    contentDescription = detailState.movie?.title,
                    modifier = Modifier.size(70.dp)
                )
            }
        } else if (posterImageState is AsyncImagePainter.State.Success) {
            backDropImageState.painter?.let {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    painter = it,
                    contentDescription = detailState.movie?.title,
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(160.dp)
                    .height(240.dp)
            ) {
                if (posterImageState is AsyncImagePainter.State.Error) {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(70.dp),
                            imageVector = Icons.Rounded.ImageNotSupported,
                            contentDescription = detailState.movie?.title
                        )
                    }
                }

                if (posterImageState is AsyncImagePainter.State.Success) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp)),
                        painter = posterImageState.painter,
                        contentDescription = detailState.movie?.title,
                        contentScale = ContentScale.Crop
                    )
                }
            }

            detailState.movie?.let { movie ->
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = movie.title,
                        fontSize = 19.sp,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                        RatingBar(
                            starsModifier = Modifier.size(18.dp),
                            rating = movie.voteAverage / 2
                        )
                        Text(
                            text = movie.voteAverage.toString(),
                            modifier = Modifier.padding(start = 4.dp),
                            color = Color.LightGray,
                            fontSize = 14.sp,
                            maxLines = 1,
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = stringResource(R.string.language) + movie.originalLanguage
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = stringResource(R.string.release_date) + movie.releaseDate
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = movie.releaseDate + stringResource(R.string.votes)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(R.string.overview),
            fontSize = 19.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        detailState.movie?.let {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = it.overview,
                fontSize = 16.sp,
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun createImageState(imgPath: String) = rememberAsyncImagePainter(
    model = ImageRequest.Builder(LocalContext.current).data(imgPath).size(Size.ORIGINAL).build()
).state