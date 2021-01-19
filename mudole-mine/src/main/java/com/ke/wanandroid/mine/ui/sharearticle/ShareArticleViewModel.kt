package com.ke.wanandroid.mine.ui.sharearticle

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.Result
import com.ke.mvvm.base.livedata.SingleLiveEvent
import com.ke.mvvm.base.model.SnackbarAction
import com.ke.mvvm.base.ui.BaseViewModel
import com.ke.wanandroid.common.event.EventBus
import com.ke.wanandroid.common.event.UserShareArticleEvent
import kotlinx.coroutines.launch

class ShareArticleViewModel @ViewModelInject constructor(
    private val shareArticleRepository: ShareArticleRepository,
    private val eventBus: EventBus
) :
    BaseViewModel() {

    private val _shareResult = SingleLiveEvent<Boolean>()

    val shareResult: LiveData<Boolean>
        get() = _shareResult


    fun shareArticle(title: String, link: String) {
        viewModelScope.launch {
            _loadingViewVisible.value = true
            val result = shareArticleRepository.shareArticle(title, link)
            _loadingViewVisible.value = false
            when (result) {
                is Result.Success -> {
                    if (result.data.isSuccess) {
                        _shareResult.value = true
                        eventBus.post(UserShareArticleEvent())
                    } else {
                        _snackbarEvent.value = SnackbarAction(message = result.data.errorMsg)
                    }
                }
                is Result.Error -> {
                    _snackbarEvent.value =
                        SnackbarAction(message = "发生了一些错误，需要重试吗？", actionName = "重试") {
                            shareArticle(title, link)
                        }
                }
            }
        }
    }
}