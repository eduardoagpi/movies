package com.cesaraguirre.movies.presentation.viewmodel

import com.cesaraguirre.movies.domain.entity.Movie
import com.cesaraguirre.movies.domain.interactor.GetUpcomingMoviesUseCase
import com.cesaraguirre.movies.domain.interactor.UpdateUpcomingMoviesUseCase
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UpcomingMoviesViewModel @Inject constructor(
        private val updateUpcomingMoviesUseCase: UpdateUpcomingMoviesUseCase,
        private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase
) : BaseMovieListViewModel() {

    override fun getMovieListToDisplay(): Single<List<Movie>> = getUpcomingMoviesUseCase.execute()

    override fun updateMovieListRepository(): Completable = updateUpcomingMoviesUseCase.execute()

}