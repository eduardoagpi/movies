package com.cesaraguirre.movies.presentation.viewstate

sealed class MovieDetailViewState {
    object FetchingMovieViewState: MovieDetailViewState()
    data class MovieDetailFetchedViewState(
            val title: String,
            val overview: String,
            val releaseDate: String,
            val numberStars: Short,
            val posterUrl: String,
            val videosUrlsForMovie: List<String>
    ): MovieDetailViewState()
    object MovieNotFound: MovieDetailViewState()
}


