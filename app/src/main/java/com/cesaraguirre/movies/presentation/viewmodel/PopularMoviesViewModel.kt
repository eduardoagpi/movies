package com.cesaraguirre.movies.presentation.viewmodel

import com.cesaraguirre.movies.domain.entity.Movie
import com.cesaraguirre.movies.domain.interactor.GetPopularMoviesUseCase
import com.cesaraguirre.movies.domain.interactor.GetTopRatedMoviesUseCase
import com.cesaraguirre.movies.domain.interactor.UpdatePopularMoviesUseCase
import com.cesaraguirre.movies.domain.interactor.UpdateTopRatedMoviesUseCase
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class PopularMoviesViewModel @Inject constructor(
        private val updatePopularMoviesUseCase: UpdatePopularMoviesUseCase,
        private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : BaseMovieListViewModel() {

    override fun getMovieListToDisplay(): Single<List<Movie>> = getPopularMoviesUseCase.execute()

    override fun updateMovieListRepository(): Completable = updatePopularMoviesUseCase.execute()

}