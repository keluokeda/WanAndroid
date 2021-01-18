package com.ke.mvvm.base.ui

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter

abstract class BaseDataListFragment(layoutId: Int) : BaseFragment(layoutId) {

    abstract val emptyLayoutId: Int

    open fun <T> setupAdapter(
        swipeRefreshLayout: SwipeRefreshLayout,
        baseDataListViewModel: BaseDataListViewModel<*, T>,
        adapter: BaseQuickAdapter<T, *>, recyclerView: RecyclerView
    ) {
        if (recyclerView.adapter != null) {
            throw RuntimeException("请不要为Recycler设置apter，不然会在加载数据的时候显示空布局")
        }
        baseDataListViewModel.dataList.observe(viewLifecycleOwner) {
            if (recyclerView.adapter == null) {
                recyclerView.adapter = adapter
                adapter.setEmptyView(emptyLayoutId)
            }

            adapter.setList(it)
        }
        baseDataListViewModel.isRefreshing.observe(viewLifecycleOwner) {
            swipeRefreshLayout.isRefreshing = it
        }
        baseDataListViewModel.loadDataResult.observe(viewLifecycleOwner) {
//            if (recyclerView.adapter == null) {
//                recyclerView.adapter = adapter
//                adapter.setEmptyView(emptyLayoutId)
//
//            }
            when (it) {
                BaseDataListViewModel.LOAD_DATA_RESULT_SUCCESS -> {
                    adapter.loadMoreModule.loadMoreComplete()
                }
                BaseDataListViewModel.LOAD_DATA_RESULT_END -> {
                    adapter.loadMoreModule.loadMoreEnd()
                }
                BaseDataListViewModel.LOAD_DATA_RESULT_ERROR -> {
                    adapter.loadMoreModule.loadMoreFail()
                }
            }
        }
        swipeRefreshLayout.setOnRefreshListener {
            baseDataListViewModel.refresh()
        }

        //点击重试会回调这个方法
        adapter.loadMoreModule.setOnLoadMoreListener {
            baseDataListViewModel.onLoadMore()
        }

    }

    protected open fun setupRetry(
        retryView: View,
        contentView: View,
        viewModel: BaseDataListViewModel<*, *>
    ) {
        retryView.setOnClickListener {
            viewModel.retry()
        }
        viewModel.retryViewVisible.observe(viewLifecycleOwner) {
            retryView.isVisible = it
            contentView.isVisible = !it
        }
    }
}

