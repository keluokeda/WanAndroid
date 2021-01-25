package com.ke.wanandroid.ui.answer

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.ViewBindingViewHolder
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.R
import com.ke.wanandroid.common.databinding.ItemArticleBinding
import com.ke.wanandroid.common.databinding.LayoutBaseRefreshListRetryBinding
import com.ke.wanandroid.common.ui.BaseArticleListFragment
import com.ke.wanandroid.common.ui.BaseArticleListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnswerFragment : BaseArticleListFragment(R.layout.layout_base_refresh_list_retry) {

    private var isFirstResume = true

    private val viewModel: AnswerViewModel by viewModels()

    override val baseArticleListViewModel: BaseArticleListViewModel<*>
        get() = viewModel

    private val binding: LayoutBaseRefreshListRetryBinding by viewbind()

    override fun onResume() {
        super.onResume()
        if (isFirstResume) {
            isFirstResume = false
            viewModel.refresh()
        }
    }

    override fun bindData(
        holder: ViewBindingViewHolder<ItemArticleBinding>,
        item: WanArticleResponse
    ) {
        super.bindData(holder, item)
        holder.viewBinding.isNew.isVisible = false
        holder.viewBinding.tag.isVisible = false
        holder.viewBinding.desc.text = Html.fromHtml(item.desc)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRetry(binding.retry, binding.recyclerView, viewModel)
        setRefreshAndLoadMore(binding.swipeRefreshLayout, viewModel, adapter, binding.recyclerView)

    }
}