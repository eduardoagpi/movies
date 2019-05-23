package com.cesaraguirre.movies.data.networking.response

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
        @SerializedName("page") var page: Int,
        @SerializedName("results") var results: List<MovieResponse>
)