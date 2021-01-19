package com.ke.wanandroid.mine.ui.sharedarticles

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.launcher.ARouter
import com.hi.dhl.binding.viewbind
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.common.databinding.LayoutBaseListBinding
import com.ke.wanandroid.common.ui.BaseArticleListFragment
import com.ke.wanandroid.common.ui.BaseArticleListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserSharedArticlesFragment :
    BaseArticleListFragment(com.ke.wanandroid.common.R.layout.layout_base_list) {


    private val viewModel: UserSharedArticlesViewModel by viewModels()
    private val binding: LayoutBaseListBinding by viewbind()
    override val baseArticleListViewModel: BaseArticleListViewModel<*>
        get() = viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter(binding.swipeRefreshLayout, viewModel, adapter, binding.recyclerView)


        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        viewModel.userInfo.observe(viewLifecycleOwner) {
            binding.toolbar.title = it.first
//            binding.toolbar.subtitle = it.second
        }


        setupRetry(binding.retry, binding.recyclerView, viewModel)


    }
}