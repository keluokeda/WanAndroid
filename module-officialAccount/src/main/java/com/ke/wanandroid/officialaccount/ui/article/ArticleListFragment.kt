package com.ke.wanandroid.officialaccount.ui.article


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.ViewBindingViewHolder
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.databinding.ItemArticleBinding
import com.ke.wanandroid.common.log
import com.ke.wanandroid.common.ui.BaseArticleListFragment
import com.ke.wanandroid.common.ui.BaseArticleListViewModel
import com.ke.wanandroid.officialaccount.R
import com.ke.wanandroid.officialaccount.databinding.OfficialAccountsFragmentArticleListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleListFragment :
    BaseArticleListFragment(R.layout.official_accounts_fragment_article_list) {


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
        "$this onViewCreated".log()
        super.onViewCreated(view, savedInstanceState)

        setupRetry(binding.retry, binding.recyclerView, viewModel)

//        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )


        setupAdapter(binding.swipeRefreshLayout, viewModel, adapter, binding.recyclerView)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        "$this onDestroyView".log()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        "$this onAttach".log()

    }

    override fun onDetach() {
        super.onDetach()
        "$this onDetach".log()

    }

    fun setKeyWord(keyword: String?) {
        viewModel.keyword = keyword
        hideKeyboard()
    }

    companion object {
        fun createInstance(id: Int): ArticleListFragment {
            val fragment = ArticleListFragment()
            fragment.arguments = bundleOf("id" to id)
            return fragment
        }
    }
}