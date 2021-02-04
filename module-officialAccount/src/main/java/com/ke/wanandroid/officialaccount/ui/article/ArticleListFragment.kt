package com.ke.wanandroid.officialaccount.ui.article


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
class ArticleListFragment :
    BaseArticleListFragment(R.layout.layout_base_refresh_list_retry) {

    private var isFirstResume = true


    override fun bindData(
        holder: ViewBindingViewHolder<ItemArticleBinding>,
        item: WanArticleResponse
    ) {
        super.bindData(holder, item)
        holder.viewBinding.isNew.isVisible = false
        holder.viewBinding.tag.isVisible = false
    }

    private val binding: LayoutBaseRefreshListRetryBinding by viewbind()

    override val articleListViewModel: ArticleListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRetry(binding.retry, binding.recyclerView, articleListViewModel)
//        setRefreshAndLoadMore(binding.swipeRefreshLayout, viewModel, adapter, binding.recyclerView)

        setupRefreshAndLoadMore(
            binding.swipeRefreshLayout,
            articleListViewModel,
            articleAdapter,
            binding.recyclerView
        )
        setupSnackbar(articleListViewModel)
    }

    fun setKeyWord(keyword: String) {
        articleListViewModel.keyword = keyword
    }

    override fun onResume() {
        super.onResume()
        if (isFirstResume) {
            articleListViewModel.keyword = null
            isFirstResume = false
        }
    }

    companion object {
        fun createInstance(id: Int): ArticleListFragment {
            val fragment = ArticleListFragment()
            fragment.arguments = bundleOf("id" to id)
            return fragment
        }
    }
}