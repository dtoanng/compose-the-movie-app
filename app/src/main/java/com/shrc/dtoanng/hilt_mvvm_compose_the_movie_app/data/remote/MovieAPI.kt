package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.remote

import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.remote.response.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/{category}")
    suspend fun getMovieList(
        @Path("category") category: String,
        @Query("page") page: Int,
        @Query("with_genres") genreId: String?,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieListDto

    companion object {
        val BASE_URL = "https://api.themoviedb.org/3/"
        val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w342"
        val API_KEY = "f89deae4c37cdd65e0ece9d725be58fb" // public api key for testing purpose
    }
}