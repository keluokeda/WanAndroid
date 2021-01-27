package com.ke.mvvm.base.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.domian.GetDataListUseCase
import com.ke.mvvm.base.model.SnackbarAction
import kotlinx.coroutines.launch

abstract class BaseRefreshAndLoadMoreViewModel<P, R>(private val getDataListUseCase: GetDataListUseCase<P, R>) :
    BaseListViewModel<R>() {

    private var currentIndex: Int = 0


    protected open val startIndex = 1

    /**
     * 请求参数
     */
    protected abstract val parameters: P
    protected val _dataList = MutableLiveData<List<R>>()

    override val dataList: LiveData<List<R>>
        get() = _dataList

    private val _isRefreshing = MutableLiveData<Boolean>()

    /**
     * 是否显示刷新指示器
     */
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    private val _loadDataResult = MutableLiveData<Int>()

    val loadDataResult: LiveData<Int>
        get() = _loadDataResult


    open fun refresh() {
        loadData(true)
    }

    protected open fun loadData(forceRefresh: Boolean = false) {

        viewModelScope.launch {
            if (forceRefresh) {
                currentIndex = startIndex
                _isRefreshing.value = true
            }
            val result = getDataListUseCase.invoke(currentIndex, parameters)
            _isRefreshing.value = false

            if (result.isSuccess) {
                val currentList =
                    _dataList.value ?: emptyList()
                _dataList.value =
                    (if (forceRefresh) emptyList() else currentList) + result.list
                currentIndex++
                _loadDataResult.value =
                    if (result.over) LOAD_DATA_RESULT_END else LOAD_DATA_RESULT_SUCCESS

            } else {
                _snackbarEvent.value = SnackbarAction(message = result.errorMessage)
                _loadDataResult.value = LOAD_DATA_RESULT_ERROR
            }


        }


    }


    open fun loadMore() {
        loadData()
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