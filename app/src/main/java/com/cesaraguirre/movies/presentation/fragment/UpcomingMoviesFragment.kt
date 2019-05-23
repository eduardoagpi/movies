package com.cesaraguirre.movies.presentation.fragment

import com.cesaraguirre.movies.presentation.viewmodel.UpcomingMoviesViewModel

class UpcomingMoviesFragment: ListMoviesFragment<UpcomingMoviesViewModel>()  {

    override fun getViewModelClass(): Class<UpcomingMoviesViewModel> = UpcomingMoviesViewModel::class.java

}