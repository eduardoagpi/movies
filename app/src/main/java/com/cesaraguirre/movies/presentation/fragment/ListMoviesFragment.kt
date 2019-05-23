package com.cesaraguirre.movies.presentation.fragment

import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cesaraguirre.movies.R
import com.cesaraguirre.movies.presentation.activity.MovieDetailActivity
import com.cesaraguirre.movies.presentation.adapter.MoviesAdapter
import com.cesaraguirre.movies.presentation.adapter.MoviesAdapterClickListener
import com.cesaraguirre.movies.presentation.viewmodel.BaseMovieListViewModel
import com.cesaraguirre.movies.presentation.viewmodel.factory.ViewModelFactory
import com.cesaraguirre.movies.presentation.viewstate.MovieListViewState
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import javax.inject.Inject
import androidx.core.app.ActivityOptionsCompat
import kotlinx.android.synthetic.main.item_movie.*


abstract class ListMoviesFragment<VM: BaseMovieListViewModel> : androidx.fragment.app.Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: VM

    val adapterItemClickListener = object : MoviesAdapterClickListener {
        override fun onClickMovieItem(movieClickedId: Long) {
            openMovieDetailActivity(movieClickedId)
        }
    }

    protected abstract fun getViewModelClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get( getViewModelClass() )
    }

    private fun refreshUI(it: MovieListViewState) {
        progress_circular.visibility = if (it.isRefreshing) {
            View.VISIBLE
        } else {
            View.GONE
        }

        when (it) {
            is MovieListViewState.MovieListWithNoResults -> {
                moviesList.visibility = View.GONE
                text_no_results.visibility = View.VISIBLE
            }
            is MovieListViewState.MovieListWithResults -> {
                moviesList.visibility = View.VISIBLE
                text_no_results.visibility = View.GONE
                moviesList.adapter = MoviesAdapter(it.movies, adapterItemClickListener)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        moviesList.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this.context)
        moviesList.adapter = MoviesAdapter(emptyList(), adapterItemClickListener)

        viewModel.getViewStateLiveData().observe(this, Observer {
            if (it != null) {
                refreshUI(it)
            }
        })
        viewModel.requestToRefreshData()
    }

    fun openMovieDetailActivity(movieId: Long) {
        context?.let {
            val options = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(this.context as Activity, movie_item_image as View, "movie_image")
            startActivity(MovieDetailActivity.getIntent(it, movieId), options.toBundle())
        }
    }

}
