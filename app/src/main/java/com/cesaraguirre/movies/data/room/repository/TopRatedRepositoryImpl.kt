package com.cesaraguirre.movies.data.room.repository

import com.cesaraguirre.movies.data.networking.MoviesAPI
import com.cesaraguirre.movies.data.networking.NetworkConstants.API_KEY
import com.cesaraguirre.movies.data.networking.response.MovieResponseMapper
import com.cesaraguirre.movies.data.room.dao.TopRatedMovieDao
import com.cesaraguirre.movies.data.room.db.MyAppRoomDatabase
import com.cesaraguirre.movies.data.room.mapper.TopRatedMovieDataMapper
import com.cesaraguirre.movies.domain.entity.TopRatedMovie
import com.cesaraguirre.movies.domain.repository.MovieRepository
import com.cesaraguirre.movies.domain.repository.TopRatedMovieRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class TopRatedRepositoryImpl @Inject constructor(
        roomDatabase: MyAppRoomDatabase,
        private val movieRepository: MovieRepository,
        private val moviesAPI: MoviesAPI,
        private val movieResponseMapper: MovieResponseMapper,
        private val topRatedDataMapper: TopRatedMovieDataMapper
): TopRatedMovieRepository {

    private val topRatedMovieDao: TopRatedMovieDao = roomDatabase.topRatedDAO

    override fun update(): Completable {

        return moviesAPI
                .getTopRated(API_KEY)
                .flatMap {
                    val movies = movieResponseMapper.mapResponseToDomainModels(it)
                    movieRepository.addOrUpdate(movies).andThen(
                            Single.just(it)
                    )
                }.flatMap {
                    deleteAll().andThen(Single.just(it))
                }.flatMap {
                    val entries = mutableListOf<TopRatedMovie>()
                    for ((index, value) in it.results.withIndex()) {
                        entries.add(
                                TopRatedMovie(index, value.id.toLong())
                        )
                    }
                    addOrUpdate(entries).andThen(Single.just(it))
                }.ignoreElement()
    }

    override fun getTopRatedEntries(): Single<List<TopRatedMovie>> {
        return topRatedMovieDao.topRatedEntriesOrdered.map {
            topRatedDataMapper.mapToDomain(it)
        }
    }

    override fun deleteAll(): Completable {
        return topRatedMovieDao.deleteAll()
    }

    override fun addOrUpdate(movies: List<TopRatedMovie>): Completable{
        return topRatedMovieDao.addOrUpdate(
                topRatedDataMapper.mapToRoom(movies)
        )
    }
}