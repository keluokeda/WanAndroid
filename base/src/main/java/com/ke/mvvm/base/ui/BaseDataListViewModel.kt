package com.ke.mvvm.base.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.BaseDataListRepository
import com.ke.mvvm.base.data.Result

import kotlinx.coroutines.launch
import java.lang.Exception

abstract class BaseDataListViewModel<Params, R>(private val baseDataListRepository: BaseDataListRepository<Params, R>) :
    BaseViewModel() {
    open val startIndex = 1

    var index = 0


    abstract val params: Params

    private val _isRefreshing = MutableLiveData<Boolean>()

    /**
     * 是否显示刷新指示器
     */
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing


    protected val _dataList = MutableLiveData<List<R>>()

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
     * 重试
     */
    fun retry() {
        loadData(true)
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
    protected open fun loadData(forceRefresh: Boolean = false) {
        viewModelScope.launch {
            onLoadDataStart(forceRefresh)

            val result =
                baseDataListRepository.getDataList(index, params)
            when (result) {
                is Result.Success -> {
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
                is Result.Error -> {
                    onLoadDataError(result.exception)
                }
            }
            onLoadDataFinish(forceRefresh)
        }
    }

    /**
     * 加载数据完成的时候会调用这个方法
     */
    protected open fun onLoadDataFinish(forceRefresh: Boolean) {
        _isRefreshing.value = false
    }

    /**
     * 加载数据出错的时候会回调这个方法
     */
    protected open fun onLoadDataError(exception: Exception) {
        _loadDataResult.value = LOAD_DATA_RESULT_ERROR
        if (_dataList.value == null) {
            _retryViewVisible.value = true
        }
    }

    /**
     * 开始加载数据的时候会回调这个方法
     */
    protected open fun onLoadDataStart(forceRefresh: Boolean) {
        if (forceRefresh) {
            _isRefreshing.value = true
            index = startIndex
        }
        _retryViewVisible.value = false

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