package com.ke.mvvm.base.ui

import androidx.lifecycle.LiveData


abstract class BaseListViewModel<R>() :
    BaseViewModel() {
    abstract val dataList: LiveData<List<R>>
}