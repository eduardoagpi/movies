package com.cesaraguirre.movies.data.networking.response

import com.cesaraguirre.movies.domain.entity.MovieVideo
import javax.inject.Inject

class MovieVideosResponseMapper @Inject constructor() {

    fun mapResponseToDomainModels(input: MovieVideosResponse): List<MovieVideo> {
        val output =  mutableListOf<MovieVideo>()
        val movieId = input.id
        input.results.forEach {
            output.add(MovieVideo(movieId.toLong(), it.videoKey))
        }
        return output
    }
}