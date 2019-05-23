package com.cesaraguirre.movies.domain.interactor

import com.cesaraguirre.movies.domain.repository.PopularMovieRepository
import com.cesaraguirre.movies.domain.repository.UpcomingMovieRepository
import io.reactivex.Completable
import javax.inject.Inject

class UpdatePopularMoviesUseCase @Inject constructor(
        private val popularMoviesRepository: PopularMovieRepository
) {

    fun execute() = popularMoviesRepository.update()
}