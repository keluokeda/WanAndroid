package com.ke.wanandroid.ui.project

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.ViewBindingViewHolder
import com.ke.wanandroid.R
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.databinding.ItemArticleBinding
import com.ke.wanandroid.common.ui.BaseArticleListFragment
import com.ke.wanandroid.common.ui.BaseArticleListViewModel
import com.ke.wanandroid.databinding.FragmentProjectArticlesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectArticlesFragment : BaseArticleListFragment(R.layout.fragment_project_articles) {

    protected var isFirstResume = true

    private val viewModel: ProjectArticlesViewModel by viewModels()

    override val baseArticleListViewModel: BaseArticleListViewModel<*>
        get() = viewModel

    private val binding: FragmentProjectArticlesBinding by viewbind()

    override fun onResume() {
        super.onResume()
        if (isFirstResume) {
            isFirstResume = false
            viewModel.start()
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
        setupRetry(binding.retry, binding.recyclerView, viewModel)
        setupAdapter(binding.swipeRefreshLayout, viewModel, adapter, binding.recyclerView)

    }

    companion object {
        fun createInstance(id: Int): ProjectArticlesFragment {
            val fragment = ProjectArticlesFragment()
            fragment.arguments = bundleOf("id" to id)
            return fragment
        }
    }
}