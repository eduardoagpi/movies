package com.cesaraguirre.movies.di

import com.cesaraguirre.movies.data.room.repository.*
import com.cesaraguirre.movies.domain.repository.*
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun providesMovieRepository(implementation: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun provideTopRatedRepository(implementation: TopRatedRepositoryImpl): TopRatedMovieRepository

    @Binds
    abstract fun providePopularMovieRepository(implementation: PopularMovieRepositoryImpl): PopularMovieRepository

    @Binds
    abstract fun provideUpcomingMovieRepository(implementation: UpcomingMovieRepositoryImpl): UpcomingMovieRepository

    @Binds
    abstract fun provideVideoRepository(implementation: VideoRepositoryImpl): VideoRepository
}