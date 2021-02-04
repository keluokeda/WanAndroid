package com.ke.wanandroid.mine.ui.collections

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chad.library.adapter.base.BaseQuickAdapter
import com.hi.dhl.binding.viewbind
import com.ke.wanandroid.common.databinding.LayoutBaseListBinding
import com.ke.wanandroid.common.ui.article.ArticleDiffCallback
import com.ke.wanandroid.common.ui.article.BaseArticleListFragment
import com.ke.wanandroid.mine.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MyCollectionsFragment :
    BaseArticleListFragment(R.layout.layout_base_list) {


    private val binding: LayoutBaseListBinding by viewbind()

    override val articleListViewModel: MyCollectionsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupRetry(binding.retry, binding.recyclerView, articleListViewModel)

        setupSnackbar(articleListViewModel)

        setupRefreshAndLoadMore(
            binding.swipeRefreshLayout,
            articleListViewModel,
            articleAdapter,
            binding.recyclerView
        )
        articleAdapter.setDiffCallback(ArticleDiffCallback)
        binding.toolbar.apply {
            title = "我的收藏"
            setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun <R> bindDataList(adapter: BaseQuickAdapter<R, *>, it: List<R>) {
        adapter.setDiffNewData(it.toMutableList())
    }


}