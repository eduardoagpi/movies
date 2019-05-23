package com.cesaraguirre.movies.domain.interactor

import com.cesaraguirre.movies.domain.repository.MovieRepository
import io.reactivex.Completable
import javax.inject.Inject

class UpdateMovieRepositoryForPattern @Inject constructor(
        val movieRepository: MovieRepository
) {

    companion object {
        const val MIN_STRING_LENGTH_TO_UPDATE_REPOSITORY = 4
    }

    fun execute(pattern: String): Completable {
        return if (pattern.length >= MIN_STRING_LENGTH_TO_UPDATE_REPOSITORY ) {
            movieRepository.updateForPattern(pattern)
        } else {
            Completable.complete()
        }
    }
}