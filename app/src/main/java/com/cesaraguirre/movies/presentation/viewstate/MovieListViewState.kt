package com.cesaraguirre.movies.presentation.viewstate

sealed class MovieListViewState(val isRefreshing: Boolean) {

    data class MovieListWithNoResults(
            private val refreshing: Boolean
    ) : MovieListViewState(refreshing)

    data class MovieListWithResults(
            val movies: List<MovieListItemViewState>,
            private val refreshing: Boolean
    ): MovieListViewState(refreshing)
}