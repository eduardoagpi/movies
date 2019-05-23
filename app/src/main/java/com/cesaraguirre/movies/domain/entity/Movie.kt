package com.cesaraguirre.movies.domain.entity

import org.joda.time.LocalDate

data class Movie(
        val id: Long,
        val title: String,
        val overview: String,
        val releaseDate: LocalDate,
        val posterResource: String,
        val voteAverage: Float
)