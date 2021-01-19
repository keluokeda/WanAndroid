package com.ke.wanandroid.mine.ui.myshare

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.launcher.ARouter
import com.hi.dhl.binding.viewbind
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.common.databinding.LayoutBaseListBinding
import com.ke.wanandroid.common.ui.BaseArticleListFragment
import com.ke.wanandroid.common.ui.BaseArticleListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyShareArticlesFragment :
    BaseArticleListFragment(com.ke.wanandroid.common.R.layout.layout_base_list) {

    private val viewModel: MyShareArticleViewModel by viewModels()
    override val baseArticleListViewModel: BaseArticleListViewModel<*>
        get() = viewModel
    private val binding: LayoutBaseListBinding by viewbind()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter(binding.swipeRefreshLayout, viewModel, adapter, binding.recyclerView)


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

        setupRetry(binding.retry, binding.recyclerView, viewModel)

    }


    override fun onCreateActionPopupMenu(menu: Menu, article: WanArticleResponse) {
        super.onCreateActionPopupMenu(menu, article)
        menu.add(0, 3, 0, "删除")
    }

    override fun onMenuItemClick(menuItem: MenuItem, article: WanArticleResponse) {
        super.onMenuItemClick(menuItem, article)
        if (menuItem.itemId == 3) {
            viewModel.deleteMyShareArticle(article)
        }
    }
}