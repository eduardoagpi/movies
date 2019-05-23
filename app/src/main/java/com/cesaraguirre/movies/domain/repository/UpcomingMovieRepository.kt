package com.cesaraguirre.movies.domain.repository

import com.cesaraguirre.movies.domain.entity.UpcomingMovie
import io.reactivex.Completable
import io.reactivex.Single

interface UpcomingMovieRepository {
    fun update(): Completable
    fun getUpcomingEntries(): Single<List<UpcomingMovie>>
    fun deleteAll(): Completable
    fun addOrUpdate(movies: List<UpcomingMovie>): Completable
}