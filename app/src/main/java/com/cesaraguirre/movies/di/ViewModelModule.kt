package com.cesaraguirre.movies.di

import androidx.lifecycle.ViewModel
import com.cesaraguirre.movies.presentation.viewmodel.*
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {

    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Binds
    @IntoMap
    @ViewModelKey(PopularMoviesViewModel::class)
    internal abstract fun popularMoviesViewModel(viewModel:PopularMoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TopRatedMoviesViewModel::class)
    internal abstract fun topRatedMoviesViewModel(viewModel:TopRatedMoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UpcomingMoviesViewModel::class)
    internal abstract fun upcomingMoviesViewModel(viewModel:UpcomingMoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    internal abstract fun movieDetailViewModel(viewModel:MovieDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FindMovieViewModel::class)
    internal abstract fun findMovieViewModel(viewModel:FindMovieViewModel): ViewModel


}