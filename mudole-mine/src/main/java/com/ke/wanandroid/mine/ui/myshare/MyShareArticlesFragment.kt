package com.ke.wanandroid.mine.ui.myshare

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.hi.dhl.binding.viewbind
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.common.databinding.LayoutBaseListBinding
import com.ke.wanandroid.common.ui.article.ArticleDiffCallback
import com.ke.wanandroid.common.ui.article.BaseArticleListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyShareArticlesFragment :
    BaseArticleListFragment(com.ke.wanandroid.common.R.layout.layout_base_list) {


    private val binding: LayoutBaseListBinding by viewbind()

    override val articleListViewModel: MyShareArticleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setup(
            binding.swipeRefreshLayout,
            articleListViewModel,
            articleAdapter,
            binding.recyclerView
        )


        binding.toolbar.title = "我的分享"
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.toolbar.menu.clear()
        binding.toolbar.menu.add(0, 1, 0, "添加")
            .setIcon(com.ke.wanandroid.common.R.drawable.baseline_add_toolbar_24dp)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        binding.toolbar.setOnMenuItemClickListener {
            ARouter.getInstance().build(PagePath.SHARE_ARTICLE).navigation()
            return@setOnMenuItemClickListener true
        }

        setupRetry(binding.retry, binding.recyclerView, articleListViewModel)
        articleAdapter.setDiffCallback(ArticleDiffCallback)
    }


    override fun onCreateActionPopupMenu(menu: Menu, article: WanArticleResponse, startId: Int) {
        super.onCreateActionPopupMenu(menu, article, startId)
        menu.add(0, 4, 0, "删除")
    }

    override fun onMenuItemClick(menuItem: MenuItem, article: WanArticleResponse) {
        super.onMenuItemClick(menuItem, article)
        if (menuItem.itemId == 4) {
            articleListViewModel.deleteMyShareArticle(article)
        }
    }

    override fun <R> bindDataList(adapter: BaseQuickAdapter<R, *>, it: List<R>) {
        adapter.setDiffNewData(it.toMutableList())
    }
}