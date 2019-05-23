package com.cesaraguirre.movies.presentation.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cesaraguirre.movies.R
import com.cesaraguirre.movies.presentation.adapter.MoviesAdapterClickListener
import com.cesaraguirre.movies.presentation.adapter.SearchResultsAdapter
import com.cesaraguirre.movies.presentation.viewmodel.FindMovieViewModel
import com.cesaraguirre.movies.presentation.viewmodel.factory.ViewModelFactory
import com.cesaraguirre.movies.presentation.viewstate.SearchResultsViewState
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_find_movie.*
import javax.inject.Inject

class FindMovieActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: FindMovieViewModel
    private lateinit var adapter: SearchResultsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_movie)
        title = "Find Movie"

        viewModel = ViewModelProviders.of(this, viewModelFactory).get( FindMovieViewModel::class.java )

        //setup recycler view & adapter
        search_results.layoutManager = LinearLayoutManager(this)
        adapter = SearchResultsAdapter(object : MoviesAdapterClickListener{
            override fun onClickMovieItem(movieClickedId: Long) {
                openMovieDetailActivity(movieClickedId)
            }
        })
        search_results.adapter = adapter

        input_title_to_search.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) { }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.trim() != "") {
                    viewModel.newSearchWithString(s.toString())
                }
            }

        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.getViewStateLiveData().observe(this, Observer {
            if (it != null) {
                refreshUI(it)
            }
        })
    }

    private fun refreshUI(newViewState: SearchResultsViewState) {
        when(newViewState) {
            is SearchResultsViewState.NoResultsFound -> {
                search_results.visibility = View.GONE
                found_no_resoults.visibility = View.VISIBLE
            }
            is SearchResultsViewState.ResultsFound -> {
                search_results.visibility = View.VISIBLE
                found_no_resoults.visibility = View.GONE
                adapter.submitList(newViewState.results)
                adapter.notifyDataSetChanged()
            }
        }
    }

    fun openMovieDetailActivity(movieId: Long) {
        startActivity(MovieDetailActivity.getIntent(this, movieId))
    }

    companion object {
        fun getIntent(context: Context) : Intent {
            return Intent(context, FindMovieActivity::class.java)
        }
    }
}
