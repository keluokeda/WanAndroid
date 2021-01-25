package com.ke.wanandroid.common.ui

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.PopupMenu
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.RequestManager
import com.ke.mvvm.base.ui.ViewBindingViewHolder
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.R
import com.ke.wanandroid.common.bindArticle
import com.ke.wanandroid.common.const.ExtraKey
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.common.databinding.ItemArticleBinding
import com.ke.wanandroid.common.ui.article.ArticleAdapter

interface ArticleListView {
    val articleAdapter: ArticleAdapter
        get() {
            return ArticleAdapter { holder, item ->
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


    fun onCreateActionPopupMenu(menu: Menu, article: WanArticleResponse, startId: Int = 3) {
        if (article.collect) {
            menu.add(0, 1, 0, "取消收藏")
        } else {
            menu.add(0, 0, 0, "收藏")
        }
        menu.add(0, 2, 0, "稍后阅读")
    }

    fun onMenuItemClick(menuItem: MenuItem, article: WanArticleResponse) {

    }


    val requestManager: RequestManager


    fun bindData(
        holder: ViewBindingViewHolder<ItemArticleBinding>,
        item: WanArticleResponse
    ) {
        holder.viewBinding.bindArticle(item, requestManager)
    }
}