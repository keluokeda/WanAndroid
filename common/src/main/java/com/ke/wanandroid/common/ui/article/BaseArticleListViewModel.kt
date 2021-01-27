package com.ke.wanandroid.common.ui.article

import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.Result
import com.ke.mvvm.base.domian.GetDataListUseCase
import com.ke.mvvm.base.domian.UseCase
import com.ke.mvvm.base.model.SnackbarAction
import com.ke.mvvm.base.ui.BaseRefreshAndLoadMoreViewModel
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.api.response.WanBaseResponse
import com.ke.wanandroid.common.domain.CancelCollectArticleUseCase
import com.ke.wanandroid.common.domain.CollectArticleUseCase
import com.ke.wanandroid.common.domain.laterread.AddToLaterReadListUseCase
import kotlinx.coroutines.launch

abstract class BaseArticleListViewModel<P>(
    getDataListUseCase: GetDataListUseCase<P, WanArticleResponse>,
    private val collectArticleUseCase: CollectArticleUseCase,
    private val cancelCollectArticleUseCase: CancelCollectArticleUseCase,
    private val addToLaterReadListUseCase: AddToLaterReadListUseCase
) :
    BaseRefreshAndLoadMoreViewModel<P, WanArticleResponse>(getDataListUseCase) {
    fun collectArticle(wanArticleResponse: WanArticleResponse) {
        viewModelScope.launch {
            when (val result = collectArticleUseCase.invoke(wanArticleResponse.id)) {
                is Result.Success -> {
                    val message = if (result.data.isSuccess) {
                        wanArticleResponse.collect = true
                        "收藏文章成功"
                    } else {
                        result.data.errorMsg
                    }

                    _snackbarEvent.value = SnackbarAction(message)
                }
                is Result.Error -> {
                    showRetrySnackBar {
                        collectArticle(wanArticleResponse)
                    }
                }
            }
        }
    }

    open fun cancelCollectArticle(wanArticleResponse: WanArticleResponse) {
        viewModelScope.launch {
            cancelCollectArticleInner(
                wanArticleResponse, wanArticleResponse.id, cancelCollectArticleUseCase
            )
        }


    }

    protected suspend fun <P> cancelCollectArticleInner(
        wanArticleResponse: WanArticleResponse,
        params: P,
        useCase: UseCase<P, WanBaseResponse<Any>>,
    ) {
        val result = useCase.invoke(params)
        when (result) {
            is Result.Success -> {
                if (result.data.isSuccess) {
                    wanArticleResponse.collect = false
                } else {
                    _snackbarEvent.value = SnackbarAction(result.data.errorMsg)
                }

            }
            is Result.Error -> {
                showRetrySnackBar {
                    cancelCollectArticle(wanArticleResponse)
                }
            }
        }
    }


    fun addToLaterRead(wanArticleResponse: WanArticleResponse) {
        viewModelScope.launch {
            addToLaterReadListUseCase(wanArticleResponse)
            _snackbarEvent.value = SnackbarAction(message = "添加成功")
        }
    }

}