package com.cesaraguirre.movies.domain.interactor

import com.cesaraguirre.movies.domain.repository.UpcomingMovieRepository
import javax.inject.Inject

class UpdateUpcomingMoviesUseCase @Inject constructor(
        private val upcomingMovieRepository: UpcomingMovieRepository
) {

    fun execute() = upcomingMovieRepository.update()
}