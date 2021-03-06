package com.ke.wanandroid.ui.square

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.hi.dhl.binding.viewbind
import com.ke.wanandroid.common.R
import com.ke.wanandroid.common.databinding.LayoutBaseRefreshListRetryBinding
import com.ke.wanandroid.common.ui.article.BaseArticleListFragment

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SquareFragment : BaseArticleListFragment(R.layout.layout_base_refresh_list_retry) {

    private var isFirstResume = true


    private val viewModel: SquareViewModel by viewModels()
    override val articleListViewModel: SquareViewModel by viewModels()

    private val binding: LayoutBaseRefreshListRetryBinding by viewbind()


    override fun onResume() {
        super.onResume()
        if (isFirstResume) {
            isFirstResume = false

            viewModel.refresh()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRetry(binding.retry, binding.recyclerView, viewModel)
        setupRefreshAndLoadMore(
            binding.swipeRefreshLayout,
            viewModel,
            articleAdapter,
            binding.recyclerView
        )
        setupSnackbar(articleListViewModel)
    }
}