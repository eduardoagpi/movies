package com.cesaraguirre.movies.presentation.viewstate

sealed class SearchResultsViewState {
    object NoResultsFound: SearchResultsViewState()
    data class ResultsFound(val results: List<SearchResultViewState>): SearchResultsViewState()
}

data class SearchResultViewState(
        val movieId: Long,
        val resultTitle: String
)