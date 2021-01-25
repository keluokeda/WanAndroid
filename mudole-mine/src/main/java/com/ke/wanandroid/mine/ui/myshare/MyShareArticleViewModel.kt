package com.ke.wanandroid.mine.ui.myshare

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.Result
import com.ke.mvvm.base.model.SnackbarAction
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.event.EventBus
import com.ke.wanandroid.common.event.UserShareArticleEvent
import com.ke.wanandroid.common.ui.BaseArticleListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import kotlinx.coroutines.launch

class MyShareArticleViewModel @ViewModelInject constructor(
    private val myShareArticlesRepository: MyShareArticlesRepository,
    eventBus: EventBus
) :
    BaseArticleListViewModel<Any>(myShareArticlesRepository) {
    override val params: Any
        get() = 0

    init {
        loadData(true)
        eventBus.toObservable(UserShareArticleEvent::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                loadData(true)
            }.addTo(compositeDisposable)

    }

    fun deleteMyShareArticle(article: WanArticleResponse) {
        viewModelScope.launch {
            val result = myShareArticlesRepository.deleteArticle(article)

            when (result) {
                is Result.Success -> {
                    if (result.data.isSuccess) {
                        val list = _dataList.value ?: return@launch
                        _dataList.value = list - article
                    } else {
                        _snackbarEvent.value = SnackbarAction(message = result.data.errorMsg)
                    }
                }
                is Result.Error -> {
                    _snackbarEvent.value = SnackbarAction(message = "发生了一些错误", actionName = "重试") {
                        deleteMyShareArticle(article)
                    }
                }
            }
        }
    }
}