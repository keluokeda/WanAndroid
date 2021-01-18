package com.ke.wanandroid.mine.ui.sharedartiles

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.hi.dhl.binding.viewbind
import com.ke.wanandroid.common.ui.BaseArticleListFragment
import com.ke.wanandroid.mine.R
import com.ke.wanandroid.mine.databinding.MineFragmentUserSharedArticlesBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserSharedArticlesFragment :
    BaseArticleListFragment(R.layout.mine_fragment_user_shared_articles) {


    private val viewModel: UserSharedArticlesViewModel by viewModels()
    private val binding: MineFragmentUserSharedArticlesBinding by viewbind()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter(binding.swipeRefreshLayout, viewModel, adapter, binding.recyclerView)

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        viewModel.userInfo.observe(viewLifecycleOwner) {
            binding.toolbar.title = it.first
//            binding.toolbar.subtitle = it.second
        }
        adapter.setOnItemClickListener { _, view, position ->

        }

        setupRetry(binding.retry, binding.recyclerView, viewModel)


    }
}