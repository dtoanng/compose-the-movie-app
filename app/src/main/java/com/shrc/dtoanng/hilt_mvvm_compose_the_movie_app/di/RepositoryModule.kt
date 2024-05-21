package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.di

import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.repository.MovieRepositoryImpl
import com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.domain.repository.MovieRepository
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
    abstract fun bindMovieList(movieListRepositoryImp: MovieRepositoryImpl): MovieRepository
}