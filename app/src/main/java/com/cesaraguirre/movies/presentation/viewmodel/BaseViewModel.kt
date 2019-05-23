package com.cesaraguirre.movies.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel<VS> : ViewModel(){

    private val compositeDisposable = CompositeDisposable()
    private val viewStateLiveData: MutableLiveData<VS> = MutableLiveData()

    protected abstract fun determineFreshViewState(): Single<VS>

    fun getViewStateLiveData(): LiveData<VS> {
        postFreshUIState()
        return viewStateLiveData
    }

    protected fun postFreshUIState() {
        determineFreshViewState()
                .subscribe({
                    viewStateLiveData.postValue(it)
                }, { e -> Log.e("", e.message)})
                .disposeInBag()
    }

    protected fun Disposable.disposeInBag() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}