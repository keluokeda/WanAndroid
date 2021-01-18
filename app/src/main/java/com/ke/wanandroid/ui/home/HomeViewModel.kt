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
import com.ke.mvvm.base.ui.BaseDataListViewModel
import java.lang.Exception


class HomeViewModel @ViewModelInject constructor(
    private val homeRepository: HomeRepository
) :
    BaseDataListViewModel<Any, WanArticleResponse>(homeRepository) {


    override val params: Any
        get() = 0
    override val startIndex: Int
        get() = 0
    private val _bannerData = MutableLiveData<List<WanBannerResponse>>()

    val bannerData: LiveData<List<WanBannerResponse>>
        get() = _bannerData


    init {
        loadData(true)
    }


    fun retry(){
        loadData(true)
    }
    override fun loadData(forceRefresh: Boolean) {
        super.loadData(forceRefresh)
        viewModelScope.launch {
            val result = homeRepository.getBannerData()
            if (result is Result.Success) {
                _bannerData.value = result.data.data
            }
        }
    }

    override fun onLoadDataStart(forceRefresh: Boolean) {
        super.onLoadDataStart(forceRefresh)
        _retryViewVisible.value = false
    }

    override fun onLoadDataError(exception: Exception) {
        super.onLoadDataError(exception)
        if (_dataList.value == null) {
            _retryViewVisible.value = true
        }
    }

}