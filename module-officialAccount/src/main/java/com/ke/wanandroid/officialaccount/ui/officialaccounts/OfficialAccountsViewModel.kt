package com.ke.wanandroid.officialaccount.ui.officialaccounts

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ke.wanandroid.api.response.WanTopicResponse
import com.ke.mvvm.base.data.Result
import com.ke.mvvm.base.livedata.SingleLiveEvent
import com.ke.mvvm.base.ui.BaseViewModel
import kotlinx.coroutines.launch


class OfficialAccountsViewModel @ViewModelInject constructor(private val officialAccountsRepository: OfficialAccountsRepository) :
    BaseViewModel() {

    private val _topicList = MutableLiveData<List<WanTopicResponse>>()

    val topicList: LiveData<List<WanTopicResponse>>
        get() = _topicList

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _loadingViewVisible.value = true
            _retryViewVisible.value = false
            _contentViewVisible.value = false
            val result = officialAccountsRepository.getTopicList()
            _loadingViewVisible.value = false
            when (result) {
                is Result.Success -> {
                    _contentViewVisible.value = true
                    _topicList.value = result.data.data
                }
                is Result.Error -> {
                    _contentViewVisible.value = false
                    _retryViewVisible.value = true
                }
            }
        }
    }

}