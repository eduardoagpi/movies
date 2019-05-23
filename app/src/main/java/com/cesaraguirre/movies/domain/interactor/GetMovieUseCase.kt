package com.cesaraguirre.movies.domain.interactor

import com.cesaraguirre.movies.domain.entity.Movie
import com.cesaraguirre.movies.domain.repository.MovieRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class GetMovieUseCase @Inject constructor (
        private val movieRepository: MovieRepository
) {

    fun execute(id: Long): Single<GetMovieUseCaseResult> {
        return movieRepository
                .findById(id)
                .flatMap<GetMovieUseCaseResult> { Maybe.just(GetMovieUseCaseResult.MovieFound(it)) }
                .onErrorReturn { GetMovieUseCaseResult.MovieNotFound }
                .toSingle(GetMovieUseCaseResult.MovieNotFound)
    }
}

sealed class GetMovieUseCaseResult {
    object MovieNotFound: GetMovieUseCaseResult()
    data class MovieFound(val movie: Movie): GetMovieUseCaseResult()
}