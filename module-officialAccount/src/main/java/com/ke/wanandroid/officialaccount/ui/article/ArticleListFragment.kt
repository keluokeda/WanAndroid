package com.ke.wanandroid.officialaccount.ui.article


import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.ViewBindingViewHolder
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.databinding.ItemArticleBinding
import com.ke.wanandroid.common.ui.BaseArticleListFragment
import com.ke.wanandroid.common.ui.BaseArticleListViewModel
import com.ke.wanandroid.officialaccount.R
import com.ke.wanandroid.officialaccount.databinding.OfficialAccountsFragmentArticleListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleListFragment :
    BaseArticleListFragment(R.layout.official_accounts_fragment_article_list) {

    protected var isFirstResume = true


    override fun bindData(
        holder: ViewBindingViewHolder<ItemArticleBinding>,
        item: WanArticleResponse
    ) {
        super.bindData(holder, item)
        holder.viewBinding.isNew.isVisible = false
        holder.viewBinding.tag.isVisible = false
    }

    private val binding: OfficialAccountsFragmentArticleListBinding by viewbind()
    private val viewModel: ArticleListViewModel by viewModels()
    override val baseArticleListViewModel: BaseArticleListViewModel<*>
        get() = viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRetry(binding.retry, binding.recyclerView, viewModel)
        setupAdapter(binding.swipeRefreshLayout, viewModel, adapter, binding.recyclerView)

    }

    fun setKeyWord(keyword: String) {
        viewModel.keyword = keyword
    }

    override fun onResume() {
        super.onResume()
        if (isFirstResume) {
            viewModel.keyword = null
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