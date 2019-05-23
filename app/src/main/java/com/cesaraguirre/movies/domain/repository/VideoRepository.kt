package com.cesaraguirre.movies.domain.repository

import com.cesaraguirre.movies.domain.entity.Movie
import com.cesaraguirre.movies.domain.entity.MovieVideo
import io.reactivex.Completable
import io.reactivex.Single

interface VideoRepository {
    fun addOrUpdate(movies: List<MovieVideo>): Completable
    fun updateForMovie(movieId: Long): Completable
    fun getVideosForMovie(movieId: Long): Single<List<MovieVideo>>
}