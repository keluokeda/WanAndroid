package com.ke.wanandroid.system.ui.article

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.hi.dhl.binding.viewbind
import com.ke.wanandroid.api.response.WanTopicResponse
import com.ke.wanandroid.common.ui.BaseArticleListFragment
import com.ke.wanandroid.system.R
import com.ke.wanandroid.system.databinding.SystemFragmentArticleListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleListFragment : BaseArticleListFragment(R.layout.system_fragment_article_list) {

    private val binding: SystemFragmentArticleListBinding by viewbind()
    private val viewModel: ArticleListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val adapter = ArticleListAdapter()
        setup(
            binding.swipeRefreshLayout,
            viewModel,
            adapter,
            binding.recyclerView
        )
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.toolbar.title =
            requireArguments().getParcelable<WanTopicResponse>(ArticleListActivity.EXTRA_TOPIC)!!.name

        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    companion object {
        fun instance(wanTopicResponse: WanTopicResponse): ArticleListFragment {
            val fragment = ArticleListFragment()
            fragment.arguments = bundleOf(ArticleListActivity.EXTRA_TOPIC to wanTopicResponse)
            return fragment
        }
    }
}