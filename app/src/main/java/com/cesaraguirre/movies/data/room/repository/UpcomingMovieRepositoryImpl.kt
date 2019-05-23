package com.cesaraguirre.movies.data.room.repository

import com.cesaraguirre.movies.data.networking.MoviesAPI
import com.cesaraguirre.movies.data.networking.NetworkConstants.API_KEY
import com.cesaraguirre.movies.data.networking.response.MovieResponseMapper
import com.cesaraguirre.movies.data.room.dao.UpcomingMovieDao
import com.cesaraguirre.movies.data.room.db.MyAppRoomDatabase
import com.cesaraguirre.movies.data.room.mapper.UpcomingMovieDataMapper
import com.cesaraguirre.movies.domain.entity.UpcomingMovie
import com.cesaraguirre.movies.domain.repository.MovieRepository
import com.cesaraguirre.movies.domain.repository.UpcomingMovieRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UpcomingMovieRepositoryImpl @Inject constructor(
        roomDatabase: MyAppRoomDatabase,
        private val movieRepository: MovieRepository,
        private val moviesAPI: MoviesAPI,
        private val movieResponseMapper: MovieResponseMapper,
        private val upcomingMovieDataMapper: UpcomingMovieDataMapper
): UpcomingMovieRepository {

    private val upcomingMovieDAO: UpcomingMovieDao = roomDatabase.upcomingMovieDAO

    override fun update(): Completable {

        return moviesAPI
                .getUpcpoming(API_KEY)
                .flatMap {
                    val movies = movieResponseMapper.mapResponseToDomainModels(it)
                    movieRepository.addOrUpdate(movies).andThen(
                            Single.just(it)
                    )
                }.flatMap {
                    deleteAll().andThen(Single.just(it))
                }.flatMap {
                    val entries = mutableListOf<UpcomingMovie>()
                    for ((index, value) in it.results.withIndex()) {
                        entries.add(
                                UpcomingMovie(index, value.id.toLong())
                        )
                    }
                    addOrUpdate(entries).andThen(Single.just(it))
                }.ignoreElement()
    }

    override fun getUpcomingEntries(): Single<List<UpcomingMovie>> {
        return upcomingMovieDAO.upcomingEntriesOrdered.map {
            upcomingMovieDataMapper.mapToDomain(it)
        }
    }

    override fun deleteAll(): Completable {
        return upcomingMovieDAO.deleteAll()
    }

    override fun addOrUpdate(movies: List<UpcomingMovie>): Completable {
        return upcomingMovieDAO.addOrUpdate(
                upcomingMovieDataMapper.mapToRoom(movies)
        )
    }
}