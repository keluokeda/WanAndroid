package com.ke.wanandroid.system.ui.article

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.module.LoadMoreModule
import com.ke.mvvm.base.ui.BaseViewBindingAdapter
import com.ke.mvvm.base.ui.ViewBindingViewHolder
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.system.databinding.SystemItemArticleBinding

class ArticleListAdapter : BaseViewBindingAdapter<WanArticleResponse, SystemItemArticleBinding>(),LoadMoreModule {
    override fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): SystemItemArticleBinding {
        return SystemItemArticleBinding.inflate(inflater, parent, false)
    }

    override fun convert(
        holder: ViewBindingViewHolder<SystemItemArticleBinding>,
        item: WanArticleResponse
    ) {
        holder.viewBinding.apply {
            title.text = item.title
            time.text = item.niceDate
            author.text =if(item.author.isNotEmpty()) item.author else item.shareUser
            chapter.text = "${item.superChapterName}:${item.chapterName}"
        }
    }
}