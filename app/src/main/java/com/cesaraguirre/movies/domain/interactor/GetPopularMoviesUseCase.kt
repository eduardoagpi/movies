package com.cesaraguirre.movies.domain.interactor

import com.cesaraguirre.movies.domain.entity.Movie
import com.cesaraguirre.movies.domain.repository.MovieRepository
import com.cesaraguirre.movies.domain.repository.PopularMovieRepository
import com.cesaraguirre.movies.domain.repository.TopRatedMovieRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
        private val popularMovieRepository: PopularMovieRepository,
        private val movieRepository: MovieRepository
){

    fun execute(): Single<List<Movie>> {

        return popularMovieRepository.getPopularEntries()
                .map { popularEntries -> popularEntries.map { it.movieId } }
                .flatMap { movieRepository.findByIds(it) }
    }
}