package com.ke.wanandroid.common.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.Result
import com.ke.mvvm.base.ui.BaseViewModel
import com.ke.wanandroid.api.response.WanTopicResponse
import kotlinx.coroutines.launch

abstract class BaseCategoryViewModel(private val categoryRepository: BaseCategoryRepository) :
    BaseViewModel() {

    private val _topicList = MutableLiveData<List<WanTopicResponse>>()

    val topicList: LiveData<List<WanTopicResponse>>
        get() = _topicList


    override fun retry() {
        loadTopicList()
    }

    protected fun loadTopicList() {
        viewModelScope.launch {
            _loadingViewVisible.value = true
            _retryViewVisible.value = false

            val result = categoryRepository.getCategoryList()

            when (result) {
                is Result.Success -> {
                    _topicList.value = result.data
                    _loadingViewVisible.value = false
                    _contentViewVisible.value = true
                    _retryViewVisible.value = false
                }
                is Result.Error -> {
                    _loadingViewVisible.value = false
                    _contentViewVisible.value = false
                    _retryViewVisible.value = true
                }
            }
        }
    }
}