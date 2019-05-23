package com.cesaraguirre.movies.data.room.mapper

import com.cesaraguirre.movies.data.room.entity.RoomPopularMovie
import com.cesaraguirre.movies.data.room.entity.RoomTopRatedMovie
import com.cesaraguirre.movies.domain.entity.PopularMovie
import com.cesaraguirre.movies.domain.entity.TopRatedMovie
import javax.inject.Inject

class PopularMovieDataMapper @Inject constructor():
        BaseDataMapper<RoomPopularMovie, PopularMovie>() {

    override fun mapToRoom(input: PopularMovie): RoomPopularMovie {
        val output = RoomPopularMovie()
        output.movieId = input.movieId
        output.position = input.position
        return output
    }

    override fun mapToDomain(input: RoomPopularMovie): PopularMovie {
        return PopularMovie(
                input.position!!,
                input.movieId!!
        )
    }
}