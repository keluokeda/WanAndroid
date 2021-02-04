package com.ke.mvvm.base.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter

abstract class BaseRefreshAndLoadMoreFragment(layoutId: Int) : BaseDataListFragment(layoutId) {

    abstract val emptyLayoutId: Int



    open fun <R> setupRefreshAndLoadMore(
        swipeRefreshLayout: SwipeRefreshLayout,
        viewModel: BaseRefreshAndLoadMoreViewModel<*, R>,
        adapter: BaseQuickAdapter<R, *>,
        recyclerView: RecyclerView
    ) {
        if (recyclerView.adapter != null) {
            throw RuntimeException("请不要为Recycler设置apter，不然会在加载数据的时候显示空布局")
        }

        viewModel.dataList.observe(viewLifecycleOwner) {
            if (recyclerView.adapter == null) {
                recyclerView.adapter = adapter
                adapter.setEmptyView(emptyLayoutId)
            }

            bindDataList(adapter, it)
        }
        viewModel.isRefreshing.observe(viewLifecycleOwner) {
            swipeRefreshLayout.isRefreshing = it
        }
        viewModel.loadDataResult.observe(viewLifecycleOwner) {
            when (it) {
                BaseRefreshAndLoadMoreViewModel.LOAD_DATA_RESULT_SUCCESS -> {
                    adapter.loadMoreModule.loadMoreComplete()
                }
                BaseRefreshAndLoadMoreViewModel.LOAD_DATA_RESULT_END -> {
                    adapter.loadMoreModule.loadMoreEnd()
                }
                BaseRefreshAndLoadMoreViewModel.LOAD_DATA_RESULT_ERROR -> {
                    adapter.loadMoreModule.loadMoreFail()
                }
            }
        }
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }

        //点击重试会回调这个方法
        adapter.loadMoreModule.setOnLoadMoreListener {
            viewModel.loadMore()
        }

    }

    protected open fun <R> bindDataList(
        adapter: BaseQuickAdapter<R, *>,
        it: List<R>
    ) {
        adapter.setList(it)
    }
}