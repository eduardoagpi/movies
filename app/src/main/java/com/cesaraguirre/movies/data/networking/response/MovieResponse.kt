package com.cesaraguirre.movies.data.networking.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
        @SerializedName("id") val id: String,
        @SerializedName("title") val title: String,
        @SerializedName("poster_path") val posterPath: String,
        @SerializedName("overview") val overview: String,
        @SerializedName("release_date") val releaseDate: String,
        @SerializedName("vote_average") val voteAverage: Float
)