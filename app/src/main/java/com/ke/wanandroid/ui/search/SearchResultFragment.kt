package com.ke.wanandroid.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.hi.dhl.binding.viewbind
import com.ke.wanandroid.common.databinding.LayoutBaseListBinding
import com.ke.wanandroid.common.ui.article.BaseArticleListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultFragment :
    BaseArticleListFragment(com.ke.wanandroid.common.R.layout.layout_base_list) {
    override val articleListViewModel: SearchResultViewModel by viewModels()

    private val binding: LayoutBaseListBinding by viewbind()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRefreshAndLoadMore(
            binding.swipeRefreshLayout,
            articleListViewModel,
            articleAdapter,
            binding.recyclerView
        )
        setupRetry(binding.retry, binding.recyclerView, articleListViewModel)

        setupSnackbar(articleListViewModel)
        binding.toolbar.apply {
            title = articleListViewModel.parameters
            setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }


}