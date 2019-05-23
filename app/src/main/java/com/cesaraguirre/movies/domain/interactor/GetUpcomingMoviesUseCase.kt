package com.cesaraguirre.movies.domain.interactor

import com.cesaraguirre.movies.domain.entity.Movie
import com.cesaraguirre.movies.domain.repository.MovieRepository
import com.cesaraguirre.movies.domain.repository.TopRatedMovieRepository
import com.cesaraguirre.movies.domain.repository.UpcomingMovieRepository
import io.reactivex.Single
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(
        private val upcomingMovieRepository: UpcomingMovieRepository,
        private val movieRepository: MovieRepository
){

    fun execute(): Single<List<Movie>> {

        return upcomingMovieRepository.getUpcomingEntries()
                .map { upcomingMovieRepository -> upcomingMovieRepository.map { it.movieId } }
                .flatMap { movieRepository.findByIds(it) }
    }
}