package com.cesaraguirre.movies.domain.repository

import com.cesaraguirre.movies.domain.entity.TopRatedMovie
import io.reactivex.Completable
import io.reactivex.Single

interface TopRatedMovieRepository {
    fun update(): Completable
    fun getTopRatedEntries(): Single<List<TopRatedMovie>>
    fun deleteAll(): Completable
    fun addOrUpdate(movies: List<TopRatedMovie>): Completable
}