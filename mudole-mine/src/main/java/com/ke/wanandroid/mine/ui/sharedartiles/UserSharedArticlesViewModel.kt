package com.ke.wanandroid.mine.ui.sharedartiles

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.Result
import com.ke.mvvm.base.ui.BaseDataListViewModel
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.const.ExtraKey
import kotlinx.coroutines.launch

class UserSharedArticlesViewModel
@ViewModelInject constructor(
    private val userSharedArticlesRepository: UserSharedArticlesRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseDataListViewModel<Int, WanArticleResponse>(userSharedArticlesRepository) {

    private val userId =
        savedStateHandle.get<Int>(ExtraKey.USER_ID) ?: throw RuntimeException("需要传入用户id")
    override val params: Int
        get() = userId

    private val _userInfo = MutableLiveData<Pair<String, String>>()

    val userInfo: LiveData<Pair<String, String>>
        get() = _userInfo


    init {
        loadData(true)
    }

    override fun loadData(forceRefresh: Boolean) {
        super.loadData(forceRefresh)
        viewModelScope.launch {
            _userInfo.value = "加载中" to ""
            val result = userSharedArticlesRepository.getUserInfo(userId)

            when (result) {
                is Result.Success -> {
                    _userInfo.value = result.data
                }
                is Result.Error -> {
                    _userInfo.value = "加载失败" to ""
                }
            }
        }
    }
}