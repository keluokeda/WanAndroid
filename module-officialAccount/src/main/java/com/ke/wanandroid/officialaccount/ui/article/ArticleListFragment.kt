package com.ke.wanandroid.officialaccount.ui.article


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.chad.library.adapter.base.module.LoadMoreModule
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseViewBindingAdapter
import com.ke.mvvm.base.ui.ViewBindingViewHolder
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.log
import com.ke.wanandroid.common.ui.BaseDataListFragment

import com.ke.wanandroid.officialaccount.R
import com.ke.wanandroid.officialaccount.databinding.OfficialAccountsFragmentArticleListBinding
import com.ke.wanandroid.officialaccount.databinding.OfficialAccountsItemArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleListFragment : BaseDataListFragment(R.layout.official_accounts_fragment_article_list) {

    private val adapter =
        object : BaseViewBindingAdapter<WanArticleResponse, OfficialAccountsItemArticleBinding>(),
            LoadMoreModule {
            override fun createViewBinding(
                inflater: LayoutInflater,
                parent: ViewGroup
            ): OfficialAccountsItemArticleBinding {
                return OfficialAccountsItemArticleBinding.inflate(inflater, parent, false)
            }

            override fun convert(
                holder: ViewBindingViewHolder<OfficialAccountsItemArticleBinding>,
                item: WanArticleResponse
            ) {
                holder.viewBinding.time.text = item.niceDate
                holder.viewBinding.title.text = item.title
                holder.viewBinding.author.text = item.author
                holder.viewBinding.chapter.text = item.superChapterName + ":" + item.chapterName
            }

        }

    private val binding: OfficialAccountsFragmentArticleListBinding by viewbind()
    private val viewModel: ArticleListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        "$this onViewCreated".log()
        super.onViewCreated(view, savedInstanceState)

//        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        adapter.addChildClickViewIds(R.id.action)

        adapter.setOnItemChildClickListener { _, _, position ->

        }

        setup(binding.swipeRefreshLayout, viewModel, adapter, binding.recyclerView)

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