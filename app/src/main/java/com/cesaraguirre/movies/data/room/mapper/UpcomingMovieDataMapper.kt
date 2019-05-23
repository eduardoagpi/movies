package com.cesaraguirre.movies.data.room.mapper

import com.cesaraguirre.movies.data.room.entity.RoomUpcomingMovie
import com.cesaraguirre.movies.domain.entity.UpcomingMovie
import javax.inject.Inject

class UpcomingMovieDataMapper @Inject constructor():
        BaseDataMapper<RoomUpcomingMovie, UpcomingMovie>() {

    override fun mapToRoom(input: UpcomingMovie): RoomUpcomingMovie {
        val output = RoomUpcomingMovie()
        output.movieId = input.movieId
        output.position = input.position
        return output
    }

    override fun mapToDomain(input: RoomUpcomingMovie): UpcomingMovie {
        return UpcomingMovie(
                input.position!!,
                input.movieId!!
        )
    }
}