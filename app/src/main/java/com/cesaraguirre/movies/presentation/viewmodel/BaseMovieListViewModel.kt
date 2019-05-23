package com.cesaraguirre.movies.presentation.viewmodel

import android.util.Log
import com.cesaraguirre.movies.data.networking.NetworkConstants
import com.cesaraguirre.movies.domain.entity.Movie
import com.cesaraguirre.movies.presentation.viewstate.MovieListItemViewState
import com.cesaraguirre.movies.presentation.viewstate.MovieListViewState
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseMovieListViewModel: BaseViewModel<MovieListViewState>() {

    abstract fun getMovieListToDisplay(): Single<List<Movie>>

    abstract fun updateMovieListRepository(): Completable

    fun requestToRefreshData() {
        val getMoviesToDisplay = getMovieListToDisplay()
                .map {
                    mapMoviesToViewState(it)
                }

        updateMovieListRepository()
                .onErrorComplete()
                .andThen(getMoviesToDisplay)
                .doOnSubscribe {
                    isRefreshingData = true
                    postFreshUIState()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate {
                    isRefreshingData = false
                    postFreshUIState()
                }
                .subscribe({
                    movies = it
                }, {
                    e-> Log.e("", e.message)
                })
                .disposeInBag()
    }

    protected var movies: List<MovieListItemViewState>? = null
    protected var isRefreshingData = false

    protected fun mapMoviesToViewState(movies: List<Movie>): List<MovieListItemViewState> {
        val listOfMoviesMapped = mutableListOf<MovieListItemViewState>()
        movies.forEach {
            listOfMoviesMapped.add(
                    MovieListItemViewState(
                            it.id,
                            it.title,
                            NetworkConstants.IMAGE_URL_PREFIX + it.posterResource)
            )
        }
        return listOfMoviesMapped
    }

    override fun determineFreshViewState(): Single<MovieListViewState> {
        val cachedMovies = movies
        val viewState: MovieListViewState = if (cachedMovies.isNullOrEmpty()) {
            MovieListViewState.MovieListWithNoResults(isRefreshingData)
        } else {
            MovieListViewState.MovieListWithResults(cachedMovies, isRefreshingData)
        }
        return Single.just(viewState)
    }
}