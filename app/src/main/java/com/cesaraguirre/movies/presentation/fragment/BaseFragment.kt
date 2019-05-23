package com.cesaraguirre.movies.presentation.fragment

import androidx.fragment.app.Fragment
import com.cesaraguirre.movies.presentation.viewmodel.BaseViewModel

abstract class BaseFragment<VM: BaseViewModel<VS>, VS>: androidx.fragment.app.Fragment() {

    protected lateinit var viewModel: VM

}