package com.ke.wanandroid.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.ui.BaseViewModel
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.api.response.WanBannerResponse
import kotlinx.coroutines.launch
import com.ke.mvvm.base.data.Result


class HomeViewModel @ViewModelInject constructor(
    private val homeRepository: HomeRepository
) :
    BaseViewModel() {

    private var _currentIndex = 0

    private val _bannerData = MutableLiveData<List<WanBannerResponse>>()

    val bannerData: LiveData<List<WanBannerResponse>>
        get() = _bannerData

    private val _articleList = MutableLiveData<List<WanArticleResponse>>()

    val articleList: LiveData<List<WanArticleResponse>>
        get() = _articleList


    init {
        viewModelScope.launch {
            val result = homeRepository.getBannerData()
            if (result is Result.Success) {
                _bannerData.value = result.data.data
            }
        }

        loadArticleData()
    }

    private fun loadArticleData() {
        viewModelScope.launch {
            val result = homeRepository.getArticleList(_currentIndex)

            when (result) {
                is Result.Success -> {
                    _articleList.value =
                        (_articleList.value ?: emptyList()) + (result.data.data?.datas
                            ?: emptyList())
                }
                is Result.Error -> {
                    result.exception.printStackTrace()
                }
            }
        }
    }
}