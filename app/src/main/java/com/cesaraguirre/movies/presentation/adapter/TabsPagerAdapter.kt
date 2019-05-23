package com.cesaraguirre.movies.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.cesaraguirre.movies.presentation.fragment.PopularMoviesFragment
import com.cesaraguirre.movies.presentation.fragment.TopRatedMoviesFragment
import com.cesaraguirre.movies.presentation.fragment.UpcomingMoviesFragment

class TabsPagerAdapter(fragmentManager: FragmentManager): androidx.fragment.app.FragmentPagerAdapter(fragmentManager) {

    companion object {
        const val NUMBER_OF_PAGES = 3

        const val POPULAR_FRAGMENT_POSITION = 0
        const val TOP_RATED_FRAGMENT_POSITION = 1
        const val UPCOMING_FRAGMENT_POSITION = 2
    }

    override fun getCount() = NUMBER_OF_PAGES

    override fun getItem(position: Int): Fragment {
        return when (position) {
            POPULAR_FRAGMENT_POSITION -> PopularMoviesFragment()
            TOP_RATED_FRAGMENT_POSITION -> TopRatedMoviesFragment()
            UPCOMING_FRAGMENT_POSITION -> UpcomingMoviesFragment()
            else -> throw RuntimeException("No Fragment specified for index $position")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            POPULAR_FRAGMENT_POSITION -> "Popular"
            TOP_RATED_FRAGMENT_POSITION -> "Top Rated"
            UPCOMING_FRAGMENT_POSITION -> "Upcoming"
            else -> throw RuntimeException("No Fragment specified for index $position")
        }
    }
}