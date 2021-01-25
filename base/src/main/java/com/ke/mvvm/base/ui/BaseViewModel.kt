package com.ke.mvvm.base.ui

import androidx.annotation.CallSuper
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ke.mvvm.base.livedata.SingleLiveEvent
import com.ke.mvvm.base.model.SnackbarAction
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    protected val _snackbarEvent = SingleLiveEvent<SnackbarAction>()

    val snackbarEvent: LiveData<SnackbarAction>
        get() = _snackbarEvent


    protected val _retryViewVisible = MutableLiveData<Boolean>()

    val retryViewVisible: LiveData<Boolean>
        get() = _retryViewVisible


    protected val _loadingViewVisible = MutableLiveData<Boolean>()

    val loadingViewVisible: LiveData<Boolean>
        get() = _loadingViewVisible

    protected val _contentViewVisible = MutableLiveData<Boolean>()

    val contentViewVisible: LiveData<Boolean>
        get() = _contentViewVisible

    /**
     * 重试
     */
    open fun retry() {
    }


    @CallSuper
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    /**
     * 显示重试snackbar
     */
    @MainThread
    protected open fun showRetrySnackBar(
        message: String = "好像出了点问题，需要重试吗？",
        actionName: String = "重试",
        action: () -> Unit
    ) {
        _snackbarEvent.value = SnackbarAction(
            message = message,
            actionName = "重试",
            action = action
        )
    }


}