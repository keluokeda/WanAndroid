package com.ke.wanandroid.common.ui

import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.BaseDataListRepository
import com.ke.mvvm.base.data.Result
import com.ke.mvvm.base.model.SnackbarAction
import com.ke.mvvm.base.ui.BaseDataListViewModel
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.model.BaseArticleListRepository
import kotlinx.coroutines.launch

abstract class BaseArticleListViewModel<Params>(private val baseArticleListRepository: BaseArticleListRepository<Params>) :
    BaseDataListViewModel<Params, WanArticleResponse>(baseArticleListRepository) {

    /**
     * 收藏文章
     */
    fun collectArticle(article: WanArticleResponse) {
        if (article.collect) {
            return
        }
        viewModelScope.launch {
            when (val result = baseArticleListRepository.collectArticle(article)) {
                is Result.Success -> {
                    if (result.data.isSuccess) {
                        article.collect = true
                        _dataList.value = _dataList.value
                        _snackbarEvent.value = SnackbarAction(message = "收藏文章成功")

                    } else {
                        _snackbarEvent.value = SnackbarAction(message = result.data.errorMsg)
                    }
                }
                is Result.Error -> {
                    _snackbarEvent.value = SnackbarAction(
                        "发生了一些错误",
                        actionName = "重试"
                    ) {
                        collectArticle(article)
                    }
                }
            }
        }
    }

    /**
     * 取消收藏文章
     */
    fun cancelCollectArticle(article: WanArticleResponse) {
        if (!article.collect) {
            return
        }
        viewModelScope.launch {
            when (val result = baseArticleListRepository.cancelCollectArticle(article)) {
                is Result.Success -> {
                    if (result.data.isSuccess) {
                        onCancelCollectArticleSuccess(article)

                    } else {
                        _snackbarEvent.value = SnackbarAction(message = result.data.errorMsg)
                    }
                }
                is Result.Error -> {
                    _snackbarEvent.value = SnackbarAction(
                        "发生了一些错误",
                        actionName = "重试"
                    ) {
                        cancelCollectArticle(article)
                    }
                }
            }
        }
    }

    /**
     * 取消收藏文章成功的时候回调的方法
     */
    protected open fun onCancelCollectArticleSuccess(article: WanArticleResponse) {
        article.collect = true
        _dataList.value = _dataList.value
        _snackbarEvent.value = SnackbarAction(message = "取消收藏文章成功")
    }
}