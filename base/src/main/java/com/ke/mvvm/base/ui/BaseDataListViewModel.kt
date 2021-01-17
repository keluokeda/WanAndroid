package com.ke.mvvm.base.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.BaseDataListRepository

import kotlinx.coroutines.launch

abstract class BaseDataListViewModel<Params, R>(private val baseDataListRepository: BaseDataListRepository<Params, R>) :
    BaseViewModel() {
    open val startIndex = 1

    var index = 0


    abstract val params:Params

    private val _isRefreshing = MutableLiveData<Boolean>()

    /**
     * 是否显示刷新指示器
     */
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing


    private val _dataList = MutableLiveData<List<R>>()

    /**
     * 数据
     */
    val dataList: LiveData<List<R>>
        get() = _dataList

    private val _loadDataResult = MutableLiveData<Int>()

    val loadDataResult: LiveData<Int>
        get() = _loadDataResult

    /**
     * 触发上拉加载的时候调用这个方法
     */
    fun onLoadMore() {
        loadData()
    }




    /**
     * 刷新数据
     */
    fun refresh() {
        loadData(true)
    }

    /**
     * 加载数据
     * @param forceRefresh 强制刷新，会让View显示刷新指示器，当数据加载成功的时候，会清空之前的数据
     */
    protected fun loadData(forceRefresh: Boolean = false) {
        if (forceRefresh) {
            _isRefreshing.value = true
            index = startIndex
        }
        viewModelScope.launch {

            val result =
                baseDataListRepository.getDataList(index, params)
            when (result) {
                is com.ke.mvvm.base.data.Result.Success -> {


                    val list = result.data
                    if (list.isEmpty()) {
                        if (forceRefresh) {
                            _dataList.value = emptyList()
                        }
                        _loadDataResult.value = LOAD_DATA_RESULT_END
                    } else {
                        _dataList.value =
                            if (forceRefresh) list else ((_dataList.value ?: emptyList()) + list)

                        _loadDataResult.value = LOAD_DATA_RESULT_SUCCESS
                        index++

                    }
                }
                is com.ke.mvvm.base.data.Result.Error -> {
                    _loadDataResult.value = LOAD_DATA_RESULT_ERROR
                }
            }
            _isRefreshing.value = false
        }
    }


    companion object {

        /**
         * 加载数据成功
         */
        const val LOAD_DATA_RESULT_SUCCESS = 0

        /**
         * 加载数据出错
         */
        const val LOAD_DATA_RESULT_ERROR = 1

        /**
         * 加载数据已完成，没有更多数据
         */
        const val LOAD_DATA_RESULT_END = 2
    }
}