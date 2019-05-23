package com.cesaraguirre.movies.presentation.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.cesaraguirre.movies.R
import com.cesaraguirre.movies.presentation.viewmodel.MovieDetailViewModel
import com.cesaraguirre.movies.presentation.viewmodel.factory.ViewModelFactory
import com.cesaraguirre.movies.presentation.viewstate.MovieDetailViewState
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_movie_detail.*
import javax.inject.Inject
import android.webkit.WebChromeClient
import android.webkit.WebSettings.PluginState
import android.widget.ScrollView


class MovieDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var mediaController: MediaController

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailViewModel::class.java)
        viewModel.getViewStateLiveData().observe(this, Observer {
            if (it != null) {
                refreshUI(it)
            }
        })

        setContentView(R.layout.activity_movie_detail)
        scroll_container.fullScroll(ScrollView.FOCUS_UP)
        title = "Movie Details"


        video_view.getSettings().setJavaScriptEnabled(true)
        video_view.getSettings().setPluginState(PluginState.ON)
        video_view.setWebChromeClient(WebChromeClient())

    }

    private fun refreshUI(viewState: MovieDetailViewState) {
        when(viewState) {
            is MovieDetailViewState.FetchingMovieViewState -> {
                progress_bar.visibility = View.VISIBLE
                text_not_found.visibility = View.GONE
            }
            is MovieDetailViewState.MovieNotFound -> {
                progress_bar.visibility = View.GONE
                text_not_found.visibility = View.VISIBLE
            }
            is MovieDetailViewState.MovieDetailFetchedViewState -> {
                progress_bar.visibility = View.GONE
                text_not_found.visibility = View.GONE
                movie_item_title.text = viewState.title
                movie_description.text = viewState.overview
                text_release_date.text = viewState.releaseDate
                Glide.with(movie_item_image)
                        .load(viewState.posterUrl)
                        .into(movie_item_image)
                val starsDrawable = when (viewState.numberStars) {
                    RATE_ZERO -> R.drawable.star_0
                    RATE_ONE -> R.drawable.star_1
                    RATE_TWO -> R.drawable.star_2
                    RATE_THREE -> R.drawable.star_3
                    RATE_FOUR -> R.drawable.star_4
                    RATE_FIVE -> R.drawable.star_5
                    else -> R.drawable.star_0
                }
                stars_image.setImageDrawable(ContextCompat.getDrawable(this, starsDrawable))
                if (viewState.videosUrlsForMovie.isNotEmpty()) {
                    //video_view.loadUrl("https://www.themoviedb.org/video/play?key=bLvqoHBptjg")
                    viewState.videosUrlsForMovie.firstOrNull()?.let { video_view.loadUrl(it) }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val movieId = intent.getLongExtra(MOVIE_ID_EXTRA, NO_MOVIE_ID_PROVIDED)
        if (movieId != NO_MOVIE_ID_PROVIDED) {
            viewModel.initViewModel(movieId)
        }
    }

    companion object {
        const val MOVIE_ID_EXTRA = "movieIdExtra"
        const val NO_MOVIE_ID_PROVIDED = -1L
        const val RATE_ZERO: Short = 0
        const val RATE_ONE: Short = 1
        const val RATE_TWO: Short = 2
        const val RATE_THREE: Short = 3
        const val RATE_FOUR: Short = 4
        const val RATE_FIVE: Short = 5

        fun getIntent(context: Context, movieId: Long) : Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_ID_EXTRA, movieId)

            return intent
        }
    }
}
