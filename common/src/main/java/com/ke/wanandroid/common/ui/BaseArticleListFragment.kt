package com.ke.wanandroid.common.ui

import androidx.annotation.CallSuper
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.ke.mvvm.base.ui.ViewBindingViewHolder
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.databinding.ItemArticleBinding

abstract class BaseArticleListFragment(layoutId: Int) : BaseDataListFragment(layoutId) {

    protected val adapter by lazy {
        ArticleListAdapter(::bindData)
    }

    @CallSuper
    protected open fun bindData(
        holder: ViewBindingViewHolder<ItemArticleBinding>,
        item: WanArticleResponse
    ) {
        holder.viewBinding.apply {
            isNew.isVisible = item.fresh
            author.text = if (item.author.isNotEmpty()) item.author else item.shareUser
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
}