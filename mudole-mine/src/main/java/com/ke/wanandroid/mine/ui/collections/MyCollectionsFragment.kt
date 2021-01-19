package com.ke.wanandroid.mine.ui.collections

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.hi.dhl.binding.viewbind
import com.ke.wanandroid.common.databinding.LayoutBaseListBinding
import com.ke.wanandroid.common.ui.BaseArticleListFragment
import com.ke.wanandroid.common.ui.BaseArticleListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MyCollectionsFragment :
    BaseArticleListFragment(com.ke.wanandroid.common.R.layout.layout_base_list) {

    private val viewModel: MyCollectionsViewModel by viewModels()

    private val binding: LayoutBaseListBinding by viewbind()
    override val baseArticleListViewModel: BaseArticleListViewModel<*>
        get() = viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRetry(binding.retry, binding.recyclerView, viewModel)

        setupAdapter(binding.swipeRefreshLayout, viewModel, adapter, binding.recyclerView)

        binding.toolbar.apply {
            title = "我的收藏"
            setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }
}