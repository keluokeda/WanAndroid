package com.ke.mvvm.base.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.BaseDataListRepository
import com.ke.mvvm.base.data.ListResult
import com.ke.mvvm.base.model.SnackbarAction
import kotlinx.coroutines.launch

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
                baseDataListRepository.getListResult(index, params)
            if (result.isSuccess) {
                loadDataSuccess(result, forceRefresh)
            } else {
                onLoadDataError(result, forceRefresh)
            }
            onLoadDataFinish(forceRefresh)
        }
    }

    protected open fun loadDataSuccess(
        result: ListResult<R>,
        forceRefresh: Boolean
    ) {
        if (result.list.isEmpty()) {
            if (forceRefresh) {
                _dataList.value = emptyList()
            }
            _loadDataResult.value = LOAD_DATA_RESULT_END
        } else {
            _dataList.value =
                if (forceRefresh) result.list else ((_dataList.value
                    ?: emptyList()) + result.list)
            _loadDataResult.value =
                if (result.over) LOAD_DATA_RESULT_END else LOAD_DATA_RESULT_SUCCESS
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
    protected open fun onLoadDataError(result: ListResult<R>, forceRefresh: Boolean) {
        _isRefreshing.value = false
        if (result.canRetry) {
            _loadDataResult.value = LOAD_DATA_RESULT_ERROR
        } else {
            _loadDataResult.value = LOAD_DATA_RESULT_END
            _snackbarEvent.value = SnackbarAction(result.errorMessage)
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