package com.ke.wanandroid.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.hi.dhl.binding.viewbind
import com.ke.wanandroid.R
import com.ke.wanandroid.common.databinding.LayoutBaseRefreshListRetryBinding
import com.ke.wanandroid.common.ui.article.BaseArticleListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseArticleListFragment(R.layout.layout_base_refresh_list_retry) {

    private val homeViewModel: HomeViewModel by viewModels()

    private val binding: LayoutBaseRefreshListRetryBinding by viewbind()
    override val articleListViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)






        setupRetry(binding.retry, binding.recyclerView, homeViewModel)


        setup(
            binding.swipeRefreshLayout,
            homeViewModel,
            articleAdapter,
            binding.recyclerView
        )

        setupSnackbar(articleListViewModel)
    }
}