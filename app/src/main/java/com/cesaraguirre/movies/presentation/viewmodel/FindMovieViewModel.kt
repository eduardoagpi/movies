package com.cesaraguirre.movies.presentation.viewmodel

import android.util.Log
import com.cesaraguirre.movies.domain.interactor.FindMoviesUseCase
import com.cesaraguirre.movies.domain.interactor.FindMoviesUseCaseResult
import com.cesaraguirre.movies.domain.interactor.UpdateMovieRepositoryForPattern
import com.cesaraguirre.movies.presentation.viewstate.SearchResultViewState
import com.cesaraguirre.movies.presentation.viewstate.SearchResultsViewState
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class FindMovieViewModel @Inject constructor(
        private val findMoviesUseCase: FindMoviesUseCase,
        private val updateMovieRepositoryForPattern: UpdateMovieRepositoryForPattern
): BaseViewModel<SearchResultsViewState>() {

    private var viewState: SearchResultsViewState = SearchResultsViewState.NoResultsFound

    override fun determineFreshViewState(): Single<SearchResultsViewState> {
        return Single.just(viewState)
    }

    fun newSearchWithString(searchTerm: String) {
        searchAndDisplayMovies(searchTerm)  // <- Initially the display the matching results at the db
        updateMovieRepositoryForPattern     // <- request to update repo with given searchTerm
                .execute(searchTerm)
                .subscribe({
                    searchAndDisplayMovies(searchTerm)  // <- After service response (and already saved at local), update the results
                }, { }).disposeInBag()
    }

    private fun searchAndDisplayMovies(searchTerm: String) {
        findMoviesUseCase.execute(searchTerm)
                .doAfterTerminate {
                    postFreshUIState()
                }
                .subscribe({
                    viewState = mapMoviesResultToViewModel(it)
                }, {e-> Log.e("", e.message)})
                .disposeInBag()
    }

    private fun mapMoviesResultToViewModel(useCaseResult: FindMoviesUseCaseResult): SearchResultsViewState {
        return when (useCaseResult) {
            is FindMoviesUseCaseResult.NoResults -> SearchResultsViewState.NoResultsFound
            is FindMoviesUseCaseResult.Results -> {
                val searchResults = mutableListOf<SearchResultViewState>()
                useCaseResult.moviesFound.forEach {
                    searchResults.add(
                            SearchResultViewState(it.id, it.title)
                    )
                }
                SearchResultsViewState.ResultsFound(searchResults)
            }
        }

    }
}