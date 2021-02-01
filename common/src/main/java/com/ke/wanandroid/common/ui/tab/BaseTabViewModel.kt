package com.ke.wanandroid.common.ui.tab

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.Result
import com.ke.mvvm.base.domian.LiveDataUseCase
import com.ke.mvvm.base.domian.UseCase
import com.ke.mvvm.base.ui.BaseViewModel
import com.ke.wanandroid.common.db.Topic
import kotlinx.coroutines.launch

abstract class BaseTabViewModel(
    getTopicsUseCase: LiveDataUseCase<Unit, List<Topic>>,
    private val loadTopicsUseCase: UseCase<Unit, Unit>
) :
    BaseViewModel() {
    val topics: LiveData<List<Topic>> = getTopicsUseCase.invoke(Unit).map {
        if (it.isEmpty()) {
            _loadingViewVisible.value = true
            _contentViewVisible.value = false
            loadTopicData()
        } else {
            _loadingViewVisible.value = false
            _contentViewVisible.value = true
        }
        it
    }

    override fun retry() {
        super.retry()
        _loadingViewVisible.value = true
        _retryViewVisible.value = false
        _contentViewVisible.value = false

        loadTopicData()
    }

    private fun loadTopicData() {
        viewModelScope.launch {
            when (loadTopicsUseCase(Unit)) {
                is Result.Success -> {

                }
                is Result.Error -> {
                    if (topics.value.isNullOrEmpty()) {
                        _loadingViewVisible.value = false
                        _retryViewVisible.value = true
                        _contentViewVisible.value = false
                    }
                }
            }
        }
    }
}