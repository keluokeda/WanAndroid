package com.ke.wanandroid.common.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.ke.mvvm.base.ui.BaseDataListViewModel
import com.ke.mvvm.base.ui.ViewBindingViewHolder
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.R
import com.ke.wanandroid.common.bindArticle
import com.ke.wanandroid.common.const.ExtraKey
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.common.databinding.ItemArticleBinding
import com.ke.wanandroid.common.ui.article.ArticleAdapter

abstract class BaseArticleListFragment(layoutId: Int) : BaseDataListFragment(layoutId) {

    abstract val baseArticleListViewModel: BaseArticleListViewModel<*>

    protected val adapter by lazy {
        ArticleAdapter(::bindData).apply {
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

    protected open fun onCreateActionPopupMenu(menu: Menu, article: WanArticleResponse) {
        if (article.collect) {
            menu.add(0, 1, 0, "取消收藏")
        } else {
            menu.add(0, 0, 0, "收藏")
        }
        menu.add(0, 2, 0, "稍后阅读")
    }

    protected open fun onMenuItemClick(menuItem: MenuItem, article: WanArticleResponse) {
        when (menuItem.itemId) {
            0 -> {
                baseArticleListViewModel.collectArticle(article)
            }
            1 -> {
                baseArticleListViewModel.cancelCollectArticle(article)
            }
            2 -> {
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSnackbar(baseArticleListViewModel)
    }


    @SuppressLint("SetTextI18n")
    @CallSuper
    protected open fun bindData(
        holder: ViewBindingViewHolder<ItemArticleBinding>,
        item: WanArticleResponse
    ) {
        holder.viewBinding.bindArticle(item, Glide.with(this))
    }

    override fun <T> setRefreshAndLoadMore(
        swipeRefreshLayout: SwipeRefreshLayout,
        baseDataListViewModel: BaseDataListViewModel<*, T>,
        adapter: BaseQuickAdapter<T, *>,
        recyclerView: RecyclerView
    ) {
        super.setRefreshAndLoadMore(swipeRefreshLayout, baseDataListViewModel, adapter, recyclerView)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }
}