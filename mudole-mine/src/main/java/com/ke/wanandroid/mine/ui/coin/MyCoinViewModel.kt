package com.ke.wanandroid.mine.ui.coin

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.Result
import com.ke.mvvm.base.ui.BaseRefreshAndLoadMoreViewModel
import com.ke.wanandroid.api.response.WanCoinResponse
import com.ke.wanandroid.mine.domain.coin.GetUserCoinCountUseCase
import com.ke.wanandroid.mine.domain.coin.GetUserCoinRecordsUseCase
import kotlinx.coroutines.launch

class MyCoinViewModel @ViewModelInject constructor(
    private val getUserCoinCountUseCase: GetUserCoinCountUseCase,
     getUserCoinRecordsUseCase: GetUserCoinRecordsUseCase
) :
    BaseRefreshAndLoadMoreViewModel<Unit, WanCoinResponse>(getUserCoinRecordsUseCase) {


    init {
        loadData(true)
    }

    private val _coinCount = MutableLiveData<String>()

    val coinCount: LiveData<String>
        get() = _coinCount


    override fun loadData(forceRefresh: Boolean) {
        super.loadData(forceRefresh)
        loadCoinCount()
    }

    private fun loadCoinCount() {
        viewModelScope.launch {

            when (val result = getUserCoinCountUseCase(Unit)) {
                is Result.Success -> {

                    _coinCount.value = result.data.toString()

                }
                is Result.Error -> {

                }
            }
        }
    }

    override val parameters: Unit
        get() = Unit
}