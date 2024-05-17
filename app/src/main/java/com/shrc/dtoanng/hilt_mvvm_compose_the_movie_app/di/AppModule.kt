package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.di

import android.app.Application
import androidx.room.Room
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.movieList.data.local.movie.MovieDatabase
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.movieList.data.remote.MovieAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    @Singleton
    fun providesMovieApi(): MovieAPI {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(MovieAPI.BASE_URL).client(client).build().create(MovieAPI::class.java)
    }

    @Provides
    @Singleton
    fun providesMovieDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(app, MovieDatabase::class.java, "movie_db.db").build()
    }
}