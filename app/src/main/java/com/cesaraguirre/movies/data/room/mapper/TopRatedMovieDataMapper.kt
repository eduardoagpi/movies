package com.cesaraguirre.movies.data.room.mapper

import com.cesaraguirre.movies.data.room.entity.RoomTopRatedMovie
import com.cesaraguirre.movies.domain.entity.TopRatedMovie
import javax.inject.Inject

class TopRatedMovieDataMapper @Inject constructor():
        BaseDataMapper<RoomTopRatedMovie, TopRatedMovie>() {

    override fun mapToRoom(input: TopRatedMovie): RoomTopRatedMovie {
        val output = RoomTopRatedMovie()
        output.movieId = input.movieId
        output.position = input.position
        return output
    }

    override fun mapToDomain(input: RoomTopRatedMovie): TopRatedMovie {
        return TopRatedMovie(
                input.position!!,
                input.movieId!!
        )
    }
}