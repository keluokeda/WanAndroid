package com.ke.wanandroid.ui.project

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.ViewBindingViewHolder
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.R
import com.ke.wanandroid.common.databinding.ItemArticleBinding
import com.ke.wanandroid.common.databinding.LayoutBaseRefreshListRetryBinding
import com.ke.wanandroid.common.ui.article.BaseArticleListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectArticlesFragment :
    BaseArticleListFragment(R.layout.layout_base_refresh_list_retry) {

    private var isFirstResume = true

    override val articleListViewModel: ProjectArticlesViewModel by viewModels()

    private val binding: LayoutBaseRefreshListRetryBinding by viewbind()

    override fun onResume() {
        super.onResume()
        if (isFirstResume) {
            isFirstResume = false
            articleListViewModel.refresh()
        }
    }

    override fun bindData(
        holder: ViewBindingViewHolder<ItemArticleBinding>,
        item: WanArticleResponse
    ) {
        super.bindData(holder, item)
        holder.viewBinding.isNew.isVisible = false
        holder.viewBinding.tag.isVisible = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRetry(binding.retry, binding.recyclerView, articleListViewModel)
        setupRefreshAndLoadMore(
            binding.swipeRefreshLayout,
            articleListViewModel,
            articleAdapter,
            binding.recyclerView
        )

        setupSnackbar(articleListViewModel)

    }

    companion object {
        fun createInstance(id: Int): ProjectArticlesFragment {
            val fragment = ProjectArticlesFragment()
            fragment.arguments = bundleOf("id" to id)
            return fragment
        }
    }
}