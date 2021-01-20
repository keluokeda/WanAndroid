package com.ke.wanandroid.common.ui.tab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.Result
import com.ke.mvvm.base.ui.BaseViewModel
import com.ke.wanandroid.api.response.WanBaseListResponse
import com.ke.wanandroid.api.response.WanTopicResponse
import kotlinx.coroutines.launch

abstract class BaseTabViewModel : BaseViewModel() {
    private val _topicList = MutableLiveData<List<WanTopicResponse>>()

    val topicList: LiveData<List<WanTopicResponse>>
        get() = _topicList

    fun loadData() {
        viewModelScope.launch {
            _loadingViewVisible.value = true
            _retryViewVisible.value = false
            _contentViewVisible.value = false
            val result = getTopics()
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

    abstract suspend fun getTopics(): Result<WanBaseListResponse<WanTopicResponse>>
}