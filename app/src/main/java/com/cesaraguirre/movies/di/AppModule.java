package com.cesaraguirre.movies.di;

import android.app.Application;

import com.cesaraguirre.movies.presentation.activity.FindMovieActivity;
import com.cesaraguirre.movies.presentation.activity.MainActivity;
import com.cesaraguirre.movies.presentation.activity.MovieDetailActivity;
import com.cesaraguirre.movies.presentation.application.MyApplication;
import com.cesaraguirre.movies.presentation.fragment.ListMoviesFragment;
import com.cesaraguirre.movies.presentation.fragment.PopularMoviesFragment;
import com.cesaraguirre.movies.presentation.fragment.TopRatedMoviesFragment;
import com.cesaraguirre.movies.presentation.fragment.UpcomingMoviesFragment;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppModule {


    @Binds
    @Singleton
    // Singleton annotation isn't necessary (in this case since Application instance is unique)
    // but is here for convention.
    abstract Application application(MyApplication app);

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivityInjector();

    @ContributesAndroidInjector
    abstract MovieDetailActivity contributeMovieDetailActivityInjector();

    @ContributesAndroidInjector
    abstract FindMovieActivity contributeFindMovieActivityInjector();

    @ContributesAndroidInjector
    abstract PopularMoviesFragment contributePopularMoviesFragmentInjector();

    @ContributesAndroidInjector
    abstract TopRatedMoviesFragment contributeTopRatedMoviesFragmentInjector();

    @ContributesAndroidInjector
    abstract UpcomingMoviesFragment contributeUpcomingMoviesFragmentInjector();

}
