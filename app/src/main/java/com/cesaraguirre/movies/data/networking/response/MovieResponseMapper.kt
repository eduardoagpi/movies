package com.cesaraguirre.movies.data.networking.response

import com.cesaraguirre.movies.data.room.util.LocalDateTimeUtils
import com.cesaraguirre.movies.domain.entity.Movie
import javax.inject.Inject

class MovieResponseMapper @Inject constructor() {

    fun mapResponseToDomainModels(input: MovieListResponse): List<Movie> {

        if(input.results.isNullOrEmpty()) {
            return emptyList()
        }

        val output = mutableListOf<Movie>()
        input.results.forEach {
            val movie = Movie(
                    it.id.toLong(),
                    it.title,
                    it.overview,
                    LocalDateTimeUtils.convertStringToLocalDate(it.releaseDate),
                    it.posterPath,
                    it.voteAverage
            )
            output.add(movie)
        }
        return output
    }
}