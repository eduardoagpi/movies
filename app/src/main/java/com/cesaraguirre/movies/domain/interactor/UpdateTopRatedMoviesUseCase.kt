package com.cesaraguirre.movies.domain.interactor

import com.cesaraguirre.movies.domain.repository.TopRatedMovieRepository
import io.reactivex.Completable
import javax.inject.Inject

class UpdateTopRatedMoviesUseCase @Inject constructor(
        private val topRatedMovieRepository: TopRatedMovieRepository
) {

    fun execute() = topRatedMovieRepository.update()
}