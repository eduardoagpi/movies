package com.cesaraguirre.movies.domain.interactor

import com.cesaraguirre.movies.domain.entity.MovieVideo
import com.cesaraguirre.movies.domain.repository.VideoRepository
import io.reactivex.Single
import javax.inject.Inject

class GetMovieVideosUseCase @Inject constructor(
        private val videoRepository: VideoRepository
) {

    fun execute(movieId: Long): Single<List<MovieVideo>> {
        return videoRepository.getVideosForMovie(movieId)
    }
}