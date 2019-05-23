package com.cesaraguirre.movies.data.room.repository

import com.cesaraguirre.movies.data.networking.MoviesAPI
import com.cesaraguirre.movies.data.networking.response.MovieVideosResponseMapper
import com.cesaraguirre.movies.data.room.db.MyAppRoomDatabase
import com.cesaraguirre.movies.data.room.mapper.MovieVideoDataMapper
import com.cesaraguirre.movies.domain.entity.Movie
import com.cesaraguirre.movies.domain.entity.MovieVideo
import com.cesaraguirre.movies.domain.repository.VideoRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
        roomDatabase: MyAppRoomDatabase,
        private val moviesAPI: MoviesAPI,
        private val movieVideosResponseMapper: MovieVideosResponseMapper,
        private val movieVideoDataMapper: MovieVideoDataMapper
): VideoRepository {
    private val dao = roomDatabase.videoDao

    override fun addOrUpdate(movies: List<MovieVideo>): Completable {
        return dao.insertVideos(movieVideoDataMapper.mapToRoom(movies))
    }

    override fun updateForMovie(movieId: Long): Completable {
        return moviesAPI
                .getVideosForMovie(movieId = movieId.toString())
                .map {
                    movieVideosResponseMapper.mapResponseToDomainModels(it)
                }.flatMap {
                    dao.insertVideos(
                            movieVideoDataMapper.mapToRoom(it)
                    ).toSingle {
                        Single.just(it)
                    }
                }
                .ignoreElement()
    }

    override fun getVideosForMovie(movieId: Long): Single<List<MovieVideo>> {
        return dao.getVideosForMovie(movieId)
                .map {
                    movieVideoDataMapper.mapToDomain(it)
                }
    }
}