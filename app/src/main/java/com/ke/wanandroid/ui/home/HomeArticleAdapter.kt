package com.ke.wanandroid.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.bumptech.glide.RequestManager
import com.chad.library.adapter.base.module.LoadMoreModule
import com.ke.mvvm.base.ui.BaseViewBindingAdapter
import com.ke.mvvm.base.ui.ViewBindingViewHolder
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.databinding.ItemArticleBinding

class HomeArticleAdapter(private val requestManager: RequestManager) :
    BaseViewBindingAdapter<WanArticleResponse, ItemArticleBinding>(), LoadMoreModule {
    override fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ItemArticleBinding {
        return ItemArticleBinding.inflate(inflater, parent, false)
    }

    @SuppressLint("SetTextI18n")
    override fun convert(
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
                requestManager.load(item.envelopePic).into(image)
            }
            title.text = item.title
            desc.isVisible = item.desc.isNotEmpty()
            desc.text = item.desc
            chapter.text = item.superChapterName + ":" + item.chapterName
            time.text = item.niceDate
        }
    }

}