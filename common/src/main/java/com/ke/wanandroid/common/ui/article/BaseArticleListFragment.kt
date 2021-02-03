package com.ke.wanandroid.common.ui.article

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.ke.mvvm.base.ui.BaseRefreshAndLoadMoreViewModel
import com.ke.mvvm.base.ui.ViewBindingViewHolder
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.R
import com.ke.wanandroid.common.bindArticle
import com.ke.wanandroid.common.const.ExtraKey
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.common.databinding.ItemArticleBinding
import com.ke.wanandroid.common.ui.BaseDataListFragment

abstract class BaseArticleListFragment(layoutId: Int) : BaseDataListFragment(layoutId) {

    protected val articleAdapter by lazy {
        ArticleAdapter { holder, item ->
            bindData(holder, item)
        }.apply {
            setOnItemClickListener { _, _, position ->

                ARouter.getInstance().build(PagePath.H5_ARTICLE)
                    .withParcelable(ExtraKey.ARTICLE, getItem(position)).navigation()
            }
            addChildClickViewIds(R.id.action)

            setOnItemChildClickListener { adapter, view, position ->
                val article = adapter.getItem(position) as WanArticleResponse
                val popupMenu = PopupMenu(view.context, view)
                onCreateActionPopupMenu(popupMenu.menu, article)

                popupMenu.setOnMenuItemClickListener {
                    onMenuItemClick(it, article)
                    return@setOnMenuItemClickListener true
                }

                popupMenu.show()

            }
        }
    }

    protected abstract val articleListViewModel: BaseArticleListViewModel<*>

    protected open fun onCreateActionPopupMenu(
        menu: Menu,
        article: WanArticleResponse,
        startId: Int = 3
    ) {
        if (article.collect) {
            menu.add(0, ITEM_ID_CANCEL_COLLECT, 0, "取消收藏")
        } else {
            menu.add(0, ITEM_ID_COLLECT, 0, "收藏")
        }
        menu.add(0, ITEM_ID_LATER_READ, 0, "添加至稍后阅读")

    }

    protected open fun onMenuItemClick(menuItem: MenuItem, article: WanArticleResponse) {
        when (menuItem.itemId) {
            ITEM_ID_COLLECT -> {
                articleListViewModel.collectArticle(article)

            }
            ITEM_ID_CANCEL_COLLECT -> {
                articleListViewModel.cancelCollectArticle(article)
            }
            ITEM_ID_LATER_READ -> {
                articleListViewModel.addToLaterRead(article)
            }
            else -> {
            }
        }
    }

    override fun <R> setup(
        swipeRefreshLayout: SwipeRefreshLayout,
        viewModel: BaseRefreshAndLoadMoreViewModel<*, R>,
        adapter: BaseQuickAdapter<R, *>,
        recyclerView: RecyclerView
    ) {
        super.setup(swipeRefreshLayout, viewModel, adapter, recyclerView)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        setupSnackbar(articleListViewModel)
    }

    protected open fun bindData(
        holder: ViewBindingViewHolder<ItemArticleBinding>,
        item: WanArticleResponse
    ) {
        holder.viewBinding.bindArticle(item, Glide.with(this))
    }

    companion object {
        private const val ITEM_ID_COLLECT = 1
        private const val ITEM_ID_CANCEL_COLLECT = 2
        private const val ITEM_ID_LATER_READ = 3
    }
}