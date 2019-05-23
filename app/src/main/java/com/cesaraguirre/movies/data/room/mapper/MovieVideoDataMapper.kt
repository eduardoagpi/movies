package com.cesaraguirre.movies.data.room.mapper

import com.cesaraguirre.movies.data.room.entity.RoomMovieVideo
import com.cesaraguirre.movies.domain.entity.MovieVideo
import javax.inject.Inject

class MovieVideoDataMapper @Inject constructor():
        BaseDataMapper<RoomMovieVideo, MovieVideo>(){

    override fun mapToDomain(input: RoomMovieVideo) =
            MovieVideo(
                input.movieId,
                input.key)

    override fun mapToRoom(input: MovieVideo): RoomMovieVideo {
        val output = RoomMovieVideo()
        output.movieId = input.movieId
        output.key = input.videoKey
        return output
    }
}