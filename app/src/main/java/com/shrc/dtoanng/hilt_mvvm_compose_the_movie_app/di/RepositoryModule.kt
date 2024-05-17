package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.di

import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.movieList.data.repository.MovieListRepositoryImpl
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.movieList.domain.repository.MovieListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMovieList(movieListRepositoryImp: MovieListRepositoryImpl): MovieListRepository
}