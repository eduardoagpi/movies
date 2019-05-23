package com.cesaraguirre.movies.domain.interactor

import com.cesaraguirre.movies.domain.entity.Movie
import com.cesaraguirre.movies.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class FindMoviesUseCase @Inject constructor(
        val movieRepository: MovieRepository
) {

    companion object {
        const val MAX_NUM_RESULTS = 3
    }

    fun execute(pattern: String): Single<FindMoviesUseCaseResult> {
        return movieRepository.findMovies(pattern, MAX_NUM_RESULTS).map {
            if (it.isEmpty()) {
                return@map FindMoviesUseCaseResult.NoResults
            } else {
                return@map FindMoviesUseCaseResult.Results(it)
            }
        }
    }
}

sealed class FindMoviesUseCaseResult {
    object NoResults: FindMoviesUseCaseResult()
    data class Results(val moviesFound: List<Movie>): FindMoviesUseCaseResult()
}