package com.cesaraguirre.movies.data.networking.response

import com.google.gson.annotations.SerializedName

data class MovieVideosResponse(
        @SerializedName("id") val id: Int,
        @SerializedName("results") val results: List<VideoResultResponse>
)

data class VideoResultResponse(
        @SerializedName("id") val videoID: String,
        @SerializedName("key") val videoKey: String
)