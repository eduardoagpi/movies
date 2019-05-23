package com.cesaraguirre.movies.domain.repository

import com.cesaraguirre.movies.domain.entity.Movie
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface MovieRepository {

    fun findById(id: Long): Maybe<Movie>

    fun findByIds(ids: List<Long>): Single<List<Movie>>

    fun addOrUpdate(movie: Movie): Completable

    fun addOrUpdate(movie: List<Movie>): Completable

    fun findMovies(pattern: String, limit: Int): Single<List<Movie>>

    fun updateForPattern(pattern: String): Completable
}