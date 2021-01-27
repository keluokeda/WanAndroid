package com.ke.wanandroid.mine.ui.sharedarticles

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.Result
import com.ke.wanandroid.common.const.ExtraKey
import com.ke.wanandroid.common.domain.CancelCollectArticleUseCase
import com.ke.wanandroid.common.domain.CollectArticleUseCase
import com.ke.wanandroid.common.domain.laterread.AddToLaterReadListUseCase
import com.ke.wanandroid.common.ui.article.BaseArticleListViewModel
import com.ke.wanandroid.mine.domain.sharedarticles.GetUserInfoUseCase
import com.ke.wanandroid.mine.domain.sharedarticles.GetUserSharedArticlesUseCase
import kotlinx.coroutines.launch

class UserSharedArticlesViewModel
@ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    collectArticleUseCase: CollectArticleUseCase,
    cancelCollectArticleUseCase: CancelCollectArticleUseCase,
    getUserSharedArticlesUseCase: GetUserSharedArticlesUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    addToLaterReadListUseCase: AddToLaterReadListUseCase
) : BaseArticleListViewModel<Int>(
    getUserSharedArticlesUseCase,
    collectArticleUseCase,
    cancelCollectArticleUseCase,
    addToLaterReadListUseCase
) {

    private val userId =
        savedStateHandle.get<Int>(ExtraKey.USER_ID) ?: throw RuntimeException("需要传入用户id")
    override val parameters: Int
        get() = userId

    private val _userInfo = MutableLiveData<String>()

    val userInfo: LiveData<String>
        get() = _userInfo


    init {
        loadData(true)
    }

    override fun loadData(forceRefresh: Boolean) {
        super.loadData(forceRefresh)
        viewModelScope.launch {
            _userInfo.value = "加载中"

            when (val result = getUserInfoUseCase(userId)) {
                is Result.Success -> {
                    if (result.data.isSuccess) {
                        _userInfo.value = result.data.requireData().username
                    } else {
                        _userInfo.value = "加载失败"
                    }
                }
                is Result.Error -> {
                    _userInfo.value = "加载失败"
                }
            }
        }
    }
}