package com.cesaraguirre.movies.presentation.viewmodel

import android.util.Log
import com.cesaraguirre.movies.data.networking.NetworkConstants
import com.cesaraguirre.movies.data.networking.NetworkConstants.VIDEO_URL_PREFIX
import com.cesaraguirre.movies.data.room.util.LocalDateTimeUtils
import com.cesaraguirre.movies.domain.entity.Movie
import com.cesaraguirre.movies.domain.entity.MovieVideo
import com.cesaraguirre.movies.domain.interactor.GetMovieUseCase
import com.cesaraguirre.movies.domain.interactor.GetMovieUseCaseResult
import com.cesaraguirre.movies.domain.interactor.GetMovieVideosUseCase
import com.cesaraguirre.movies.domain.interactor.UpdateMovieVideosUseCase
import com.cesaraguirre.movies.presentation.viewstate.MovieDetailViewState
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
        private val getMovieUseCase: GetMovieUseCase,
        private val getMovieVideosUseCase: GetMovieVideosUseCase,
        private val updateMovieVideosUseCase: UpdateMovieVideosUseCase
) : BaseViewModel<MovieDetailViewState>() {

    private var fetchingInfo = false

    private var pairMovieAndVideos = Pair<Movie?, List<String>>(null, emptyList())

    fun initViewModel(movieId: Long) {

        val getVideosForMovie = getMovieVideosUseCase
                .execute(movieId)
                .flatMap {
                    if (it.isEmpty()) {
                        return@flatMap updateMovieVideosUseCase
                                .execute(movieId)
                                .onErrorComplete()
                                .andThen(getMovieVideosUseCase.execute(movieId)
                        )
                    } else {
                        return@flatMap Single.just(it)
                    }
                }


        getMovieUseCase
                .execute(movieId)
                .zipWith(getVideosForMovie, BiFunction {
                    a: GetMovieUseCaseResult, b:List<MovieVideo> -> zippedMovieWithVideos(a,b)
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    pairMovieAndVideos = it
                    fetchingInfo = false
                    postFreshUIState()
                }, {
                    e-> Log.e("", e.message)
                    fetchingInfo = false
                    postFreshUIState()
                }).disposeInBag()
    }

    private fun zippedMovieWithVideos(getMovieUseCaseResult: GetMovieUseCaseResult, associatedVideos: List<MovieVideo>)
            : Pair<Movie?, List<String>> {
        return when (getMovieUseCaseResult) {
            is GetMovieUseCaseResult.MovieNotFound -> Pair(null, emptyList())
            is GetMovieUseCaseResult.MovieFound -> Pair(
                    getMovieUseCaseResult.movie,
                    associatedVideos.map { it.videoKey }
            )
        }
    }

    override fun determineFreshViewState(): Single<MovieDetailViewState> {
        val pairMovieAndVideos = this.pairMovieAndVideos
        val movie = pairMovieAndVideos.first
        return if (movie == null ){
            Single.just(MovieDetailViewState.MovieNotFound)
        } else {
            Single.just(mapMovieToMovieViewState(movie, pairMovieAndVideos.second))
        }
    }

    private fun mapMovieToMovieViewState(movie: Movie, videoUrls: List<String>) : MovieDetailViewState.MovieDetailFetchedViewState{
        val numStars = (movie.voteAverage * 5f / 10f).toShort()
        val releaseDateRedable = LocalDateTimeUtils.convertLocalDateToHumanRedable(movie.releaseDate)

        return MovieDetailViewState.MovieDetailFetchedViewState(
                movie.title,
                movie.overview,
                releaseDateRedable,
                numStars,
                NetworkConstants.IMAGE_URL_PREFIX + movie.posterResource,
                videoUrls.map { VIDEO_URL_PREFIX + it }
        )
    }

}