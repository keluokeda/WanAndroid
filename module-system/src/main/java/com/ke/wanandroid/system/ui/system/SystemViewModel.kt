package com.ke.wanandroid.system.ui.system

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.Result
import com.ke.mvvm.base.ui.BaseViewModel
import com.ke.wanandroid.api.response.WanTopicResponse
import kotlinx.coroutines.launch

class SystemViewModel @ViewModelInject constructor(private val systemRepository: SystemRepository) :
    BaseViewModel() {



    private val _topicList = MutableLiveData<List<WanTopicResponse>>()

    val topicList: LiveData<List<WanTopicResponse>>
        get() = _topicList

    init {
        viewModelScope.launch {
            _loadingViewVisible.value = true

            val result = systemRepository.getSystemData()

            when (result) {
                is Result.Success -> {
                    _topicList.value = result.data
                }
                is Result.Error -> {

                }
            }
            _loadingViewVisible.value = false
        }
    }
}