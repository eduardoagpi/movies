package com.cesaraguirre.movies.domain.repository

import com.cesaraguirre.movies.domain.entity.PopularMovie
import io.reactivex.Completable
import io.reactivex.Single

interface PopularMovieRepository {
    fun update(): Completable
    fun getPopularEntries(): Single<List<PopularMovie>>
    fun deleteAll(): Completable
    fun addOrUpdate(movies: List<PopularMovie>): Completable
}