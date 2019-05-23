package com.cesaraguirre.movies.data.networking

import com.cesaraguirre.movies.data.networking.response.MovieListResponse
import com.cesaraguirre.movies.data.networking.response.MovieVideosResponse
import com.cesaraguirre.movies.di.NetworkModule
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAPI {

    @GET("movie/top_rated")
    fun getTopRated(@Query("api_key") apiKey : String = NetworkConstants.API_KEY) : Single<MovieListResponse>

    @GET("movie/popular")
    fun getPopular(@Query("api_key") apiKey : String = NetworkConstants.API_KEY) : Single<MovieListResponse>

    @GET("movie/upcoming")
    fun getUpcpoming(@Query("api_key") apiKey : String = NetworkConstants.API_KEY) : Single<MovieListResponse>

    @GET("search/movie")
    fun searchMovie(
            @Query("query") query: String,
            @Query("api_key") apiKey : String = NetworkConstants.API_KEY
    ): Single<MovieListResponse>

    @GET("movie/{movie_id}/videos")
    fun getVideosForMovie(
            @Path("movie_id") movieId: String,
            @Query("api_key") apiKey : String = NetworkConstants.API_KEY
    ): Single<MovieVideosResponse>
}