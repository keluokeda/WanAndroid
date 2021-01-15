package com.ke.mvvm.base.ui

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter

abstract class BaseDataListFragment(layoutId: Int) : BaseFragment(layoutId) {

    fun <T> setup(
        swipeRefreshLayout: SwipeRefreshLayout,
        baseDataListViewModel: BaseDataListViewModel<T>,
        adapter: BaseQuickAdapter<T, *>
    ) {
        baseDataListViewModel.dataList.observe(viewLifecycleOwner) {
            adapter.setList(it)
        }
        baseDataListViewModel.isRefreshing.observe(viewLifecycleOwner) {
            swipeRefreshLayout.isRefreshing = it
        }
        baseDataListViewModel.loadDataResult.observe(viewLifecycleOwner) {
            when (it) {
                BaseDataListViewModel.LOAD_DATA_RESULT_SUCCESS -> {
                    adapter.loadMoreModule.loadMoreComplete()
                }
                BaseDataListViewModel.LOAD_DATA_RESULT_END -> {
                    adapter.loadMoreModule.loadMoreEnd()
                }
                else -> {
                    adapter.loadMoreModule.loadMoreEnd()
                }
            }
        }
        swipeRefreshLayout.setOnRefreshListener {
            baseDataListViewModel.refresh()
        }

        adapter.loadMoreModule.setOnLoadMoreListener {
            baseDataListViewModel.onLoadMore()
        }

    }
}

