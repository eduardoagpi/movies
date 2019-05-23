package com.cesaraguirre.movies.data.room.repository

import com.cesaraguirre.movies.data.networking.MoviesAPI
import com.cesaraguirre.movies.data.networking.NetworkConstants
import com.cesaraguirre.movies.data.networking.response.MovieResponseMapper
import com.cesaraguirre.movies.data.room.db.MyAppRoomDatabase
import com.cesaraguirre.movies.data.room.mapper.RoomMovieMapper
import com.cesaraguirre.movies.domain.entity.Movie
import com.cesaraguirre.movies.domain.repository.MovieRepository
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
        roomDataBase: MyAppRoomDatabase,
        private val roomMovieMapper: RoomMovieMapper,
        private val moviesAPI: MoviesAPI,
        private val movieResponseMapper: MovieResponseMapper
) : MovieRepository {

    private var dao = roomDataBase.itemDAO

    override fun addOrUpdate(movies: List<Movie>): Completable {
        return dao.insertMoviesOnConflictReplace(
                roomMovieMapper.mapToRoom(movies)
        )
    }

    override fun findById(id: Long): Maybe<Movie> {
        return dao.getMovieById(id).map {
            roomMovieMapper.mapToDomain(it)
        }
    }

    override fun findByIds(ids: List<Long>): Single<List<Movie>> {
        val theList = ArrayList(ids)

        return dao.getMoviesById(theList)
                .map {
                    ArrayList( roomMovieMapper.mapToDomain(it) )
                }
    }

    override fun addOrUpdate(movie: Movie): Completable {
        return dao.insertMoviesOnConflictReplace(listOf(roomMovieMapper.mapToRoom(movie)))
    }

    override fun findMovies(pattern: String, limit: Int): Single<List<Movie>> {
        return dao.findMoviesWithPattern("%$pattern%", limit).map {
            ArrayList(roomMovieMapper.mapToDomain(it))
        }
    }

    override fun updateForPattern(pattern: String): Completable {
        return moviesAPI
                .searchMovie(pattern, NetworkConstants.API_KEY)
                .map {
                    movieResponseMapper.mapResponseToDomainModels(it)
                }.flatMap {
                    dao.insertMoviesOnConflictReplace(
                            roomMovieMapper.mapToRoom(it)
                    ).toSingle {
                        Single.just(it)
                    }
                }.ignoreElement()
    }
}