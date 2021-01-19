package com.ke.wanandroid.common.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
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
import com.ke.wanandroid.common.const.ExtraKey
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.common.databinding.ItemArticleBinding

abstract class BaseArticleListFragment(layoutId: Int) : BaseDataListFragment(layoutId) {

    abstract val baseArticleListViewModel: BaseArticleListViewModel<*>

    protected val adapter by lazy {
        ArticleListAdapter(::bindData).apply {
            setOnItemClickListener { _, _, position ->

                ARouter.getInstance().build(PagePath.H5_ARTICLE)
                    .withParcelable(ExtraKey.ARTICLE, getItem(position)).navigation()
            }
            addChildClickViewIds(R.id.action)

            setOnItemChildClickListener { adapter, view, position ->
                val article = adapter.getItem(position) as WanArticleResponse
                val popupMenu = PopupMenu(view.context, view)
                if (article.collect) {
                    popupMenu.menu.add(0, 1, 0, "取消收藏")
                } else {
                    popupMenu.menu.add(0, 0, 0, "收藏")
                }
                popupMenu.menu.add(0, 2, 0, "稍后阅读")
                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        0 -> {
                            baseArticleListViewModel.collectArticle(article)
                        }
                        1 -> {
                            baseArticleListViewModel.cancelCollectArticle(article)
                        }
                        2 -> {
                        }
                    }
                    return@setOnMenuItemClickListener true
                }

                popupMenu.show()

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
        holder.viewBinding.apply {
            isNew.isVisible = item.fresh
            author.text =
                if (item.author.isNotEmpty()) item.author else (if (item.shareUser.isNotEmpty()) item.shareUser else "匿名")
            tag.isVisible = item.tags.isNotEmpty()
            tag.text = item.tags.firstOrNull()?.name
            if (item.envelopePic.isEmpty()) {
                image.isVisible = false
            } else {
                image.isVisible = true
                Glide.with(this@BaseArticleListFragment).load(item.envelopePic).into(image)
            }
            title.text = item.title
            desc.isVisible = item.desc.isNotEmpty()
            title.maxLines = if (item.desc.isNotEmpty()) 1 else 3
            desc.text = item.desc
            chapter.text = item.superChapterName + ":" + item.chapterName
            time.text = item.niceDate
        }
    }

    override fun <T> setupAdapter(
        swipeRefreshLayout: SwipeRefreshLayout,
        baseDataListViewModel: BaseDataListViewModel<*, T>,
        adapter: BaseQuickAdapter<T, *>,
        recyclerView: RecyclerView
    ) {
        super.setupAdapter(swipeRefreshLayout, baseDataListViewModel, adapter, recyclerView)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }
}