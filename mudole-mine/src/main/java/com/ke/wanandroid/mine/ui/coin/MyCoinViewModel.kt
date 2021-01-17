package com.ke.wanandroid.mine.ui.coin

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.Result
import com.ke.mvvm.base.ui.BaseDataListViewModel
import com.ke.wanandroid.api.response.WanCoinResponse
import kotlinx.coroutines.launch

class MyCoinViewModel @ViewModelInject constructor(private val myCoinRepository: MyCoinRepository) :
    BaseDataListViewModel<Any, WanCoinResponse>(myCoinRepository) {
    override val params = 0

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
            val result = myCoinRepository.getCoinCount()

            when (result) {
                is Result.Success -> {
                    if (result.data.isSuccess) {
                        _coinCount.value = result.data.requireData().toString()
                    }
                }
                is Result.Error -> {

                }
            }
        }
    }
}