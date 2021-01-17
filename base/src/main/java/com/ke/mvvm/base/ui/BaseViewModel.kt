package com.ke.mvvm.base.ui

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ke.mvvm.base.livedata.SingleLiveEvent
import com.ke.mvvm.base.model.SnackbarAction
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    protected val _snackbarEvent = SingleLiveEvent<SnackbarAction>()

    val snackbarEvent: LiveData<SnackbarAction>
        get() = _snackbarEvent


    @CallSuper
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}