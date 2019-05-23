package com.cesaraguirre.movies.data.room.mapper

import com.cesaraguirre.movies.data.room.entity.RoomMovie
import com.cesaraguirre.movies.data.room.util.LocalDateTimeUtils
import com.cesaraguirre.movies.domain.entity.Movie
import javax.inject.Inject

class RoomMovieMapper @Inject constructor() : BaseDataMapper<RoomMovie, Movie>() {

    override fun mapToRoom(input: Movie): RoomMovie {
        val output = RoomMovie()
        output.movieId = input.id
        output.overview = input.overview
        output.posterResource = input.posterResource
        output.releaseDate = LocalDateTimeUtils.convertLocalDateToString(input.releaseDate)
        output.title = input.title
        output.voteAverage = input.voteAverage
        return output
    }

    override fun mapToDomain(input: RoomMovie): Movie {
        val movieId = input.movieId!!
        val title = input.title!!
        val overview = input.overview!!
        val posterUrl = input.posterResource!!
        val releaseDate = input.releaseDate!!
        val voteAverage = input.voteAverage!!

        return Movie(
                movieId,
                title,
                overview,
                LocalDateTimeUtils.convertStringToLocalDate(releaseDate),
                posterUrl,
                voteAverage
        )
    }

}