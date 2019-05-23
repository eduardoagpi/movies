package com.cesaraguirre.movies.data.room.repository

import com.cesaraguirre.movies.data.networking.MoviesAPI
import com.cesaraguirre.movies.data.networking.NetworkConstants.API_KEY
import com.cesaraguirre.movies.data.networking.response.MovieResponseMapper
import com.cesaraguirre.movies.data.room.dao.PopularMovieDao
import com.cesaraguirre.movies.data.room.db.MyAppRoomDatabase
import com.cesaraguirre.movies.data.room.mapper.PopularMovieDataMapper
import com.cesaraguirre.movies.domain.entity.PopularMovie
import com.cesaraguirre.movies.domain.repository.MovieRepository
import com.cesaraguirre.movies.domain.repository.PopularMovieRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class PopularMovieRepositoryImpl @Inject constructor(
        roomDatabase: MyAppRoomDatabase,
        private val movieRepository: MovieRepository,
        private val moviesAPI: MoviesAPI,
        private val movieResponseMapper: MovieResponseMapper,
        private val popularMovieDataMapper: PopularMovieDataMapper
): PopularMovieRepository {

    private val popularMovieDao: PopularMovieDao = roomDatabase.popularMovieDAO

    override fun update(): Completable {

        return moviesAPI
                .getPopular(API_KEY)
                .flatMap {
                    val movies = movieResponseMapper.mapResponseToDomainModels(it)
                    movieRepository.addOrUpdate(movies).andThen(
                            Single.just(it)
                    )
                }.flatMap {
                    deleteAll().andThen(Single.just(it))
                }.flatMap {
                    val entries = mutableListOf<PopularMovie>()
                    for ((index, value) in it.results.withIndex()) {
                        entries.add(
                                PopularMovie(index, value.id.toLong())
                        )
                    }
                    addOrUpdate(entries).andThen(Single.just(it))
                }.ignoreElement()
    }

    override fun getPopularEntries(): Single<List<PopularMovie>> {
        return popularMovieDao.popularEntriesOrdered.map {
            popularMovieDataMapper.mapToDomain(it)
        }
    }

    override fun deleteAll(): Completable {
        return popularMovieDao.deleteAll()
    }

    override fun addOrUpdate(movies: List<PopularMovie>): Completable{
        return popularMovieDao.addOrUpdate(
                popularMovieDataMapper.mapToRoom(movies)
        )
    }
}