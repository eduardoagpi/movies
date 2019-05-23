package com.cesaraguirre.movies.presentation.fragment

import com.cesaraguirre.movies.presentation.viewmodel.TopRatedMoviesViewModel

class TopRatedMoviesFragment: ListMoviesFragment<TopRatedMoviesViewModel>()  {

    override fun getViewModelClass(): Class<TopRatedMoviesViewModel> = TopRatedMoviesViewModel::class.java

}