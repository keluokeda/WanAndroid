package com.ke.wanandroid.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.Result
import com.ke.mvvm.base.ui.BaseViewModel
import com.ke.wanandroid.api.response.WanHotKeyResponse
import com.ke.wanandroid.common.db.SearchHistory
import com.ke.wanandroid.domain.search.DeleteAllHistoryUseCase
import com.ke.wanandroid.domain.search.DeleteHistoryUseCase
import com.ke.wanandroid.domain.search.GetHistoryListUseCase
import com.ke.wanandroid.domain.search.GetHotKeyUseCase
import kotlinx.coroutines.launch

class SearchHistoryViewModel @ViewModelInject constructor(
    getHistoryListUseCase: GetHistoryListUseCase,
    private val getHotKeyUseCase: GetHotKeyUseCase,
    private val deleteHistoryUseCase: DeleteHistoryUseCase,
    private val deleteAllHistoryUseCase: DeleteAllHistoryUseCase
) : BaseViewModel() {


    val historyList = getHistoryListUseCase(Unit)

    private val _hotKeyList = MutableLiveData<List<WanHotKeyResponse>>()

    val hotKeyList: LiveData<List<WanHotKeyResponse>>
        get() = _hotKeyList


    init {
        viewModelScope.launch {
            _loadingViewVisible.value = true
            val result = getHotKeyUseCase(Unit)
            _loadingViewVisible.value = false
            when (result) {
                is Result.Success -> {
                    if (result.data.isSuccess) {
                        _hotKeyList.value = result.data.data
                    }
                }
                is Result.Error -> {
                }
            }
        }
    }

    fun deleteSearchHistory(searchHistory: SearchHistory) {
        viewModelScope.launch {
            deleteHistoryUseCase(searchHistory)
        }
    }

    fun deleteAllHistory() {
        viewModelScope.launch {
            deleteAllHistoryUseCase(Unit)
        }
    }
}