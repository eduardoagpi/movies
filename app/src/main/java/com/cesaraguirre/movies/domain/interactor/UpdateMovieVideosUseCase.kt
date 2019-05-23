package com.cesaraguirre.movies.domain.interactor

import com.cesaraguirre.movies.domain.repository.VideoRepository
import javax.inject.Inject

class UpdateMovieVideosUseCase @Inject constructor(
        private val videoRepository: VideoRepository
) {

    fun execute(movieId: Long) = videoRepository.updateForMovie(movieId)
}