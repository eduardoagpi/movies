package com.cesaraguirre.movies.presentation.viewmodel

import com.cesaraguirre.movies.domain.entity.Movie
import com.cesaraguirre.movies.domain.interactor.GetTopRatedMoviesUseCase
import com.cesaraguirre.movies.domain.interactor.UpdateTopRatedMoviesUseCase
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class TopRatedMoviesViewModel @Inject constructor(
        private val updateTopRatedMoviesUseCase: UpdateTopRatedMoviesUseCase,
        private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : BaseMovieListViewModel() {

    override fun getMovieListToDisplay(): Single<List<Movie>> = getTopRatedMoviesUseCase.execute()

    override fun updateMovieListRepository(): Completable = updateTopRatedMoviesUseCase.execute()

}