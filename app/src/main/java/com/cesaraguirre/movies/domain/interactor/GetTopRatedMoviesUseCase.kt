package com.cesaraguirre.movies.domain.interactor

import com.cesaraguirre.movies.domain.entity.Movie
import com.cesaraguirre.movies.domain.repository.MovieRepository
import com.cesaraguirre.movies.domain.repository.TopRatedMovieRepository
import io.reactivex.Single
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(
        private val topRatedMovieRepository: TopRatedMovieRepository,
        private val movieRepository: MovieRepository
){

    fun execute(): Single<List<Movie>> {

        return topRatedMovieRepository.getTopRatedEntries()
                .map { topRatedEntries -> topRatedEntries.map { it.movieId } }
                .flatMap { movieRepository.findByIds(it) }
    }
}