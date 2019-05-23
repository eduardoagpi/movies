package com.cesaraguirre.movies.presentation.fragment

import com.cesaraguirre.movies.presentation.viewmodel.PopularMoviesViewModel

class PopularMoviesFragment: ListMoviesFragment<PopularMoviesViewModel>() {

    override fun getViewModelClass(): Class<PopularMoviesViewModel> = PopularMoviesViewModel::class.java
}