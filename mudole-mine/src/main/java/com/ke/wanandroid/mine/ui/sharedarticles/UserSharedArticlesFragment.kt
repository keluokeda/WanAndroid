package com.ke.wanandroid.mine.ui.sharedarticles

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.hi.dhl.binding.viewbind
import com.ke.wanandroid.common.databinding.LayoutBaseListBinding
import com.ke.wanandroid.common.ui.article.BaseArticleListFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserSharedArticlesFragment :
    BaseArticleListFragment(com.ke.wanandroid.common.R.layout.layout_base_list) {


    private val binding: LayoutBaseListBinding by viewbind()
    override val articleListViewModel: UserSharedArticlesViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRefreshAndLoadMore(
            binding.swipeRefreshLayout,
            articleListViewModel,
            articleAdapter,
            binding.recyclerView
        )


        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        articleListViewModel.userInfo.observe(viewLifecycleOwner) {
            binding.toolbar.title = it
//            binding.toolbar.subtitle = it.second
        }


        setupRetry(binding.retry, binding.recyclerView, articleListViewModel)

        setupSnackbar(articleListViewModel)

    }
}