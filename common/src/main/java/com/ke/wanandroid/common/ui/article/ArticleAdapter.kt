package com.ke.wanandroid.common.ui.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.module.LoadMoreModule
import com.ke.mvvm.base.ui.BaseViewBindingAdapter
import com.ke.mvvm.base.ui.ViewBindingViewHolder
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.databinding.ItemArticleBinding

class ArticleAdapter(
    private val converter: (
        holder: ViewBindingViewHolder<ItemArticleBinding>,
        item: WanArticleResponse
    ) -> Unit
) :
    BaseViewBindingAdapter<WanArticleResponse, ItemArticleBinding>(), LoadMoreModule {
    override fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ItemArticleBinding {
        return ItemArticleBinding.inflate(inflater, parent, false)
    }

    override fun convert(
        holder: ViewBindingViewHolder<ItemArticleBinding>,
        item: WanArticleResponse
    ) {
        converter(holder, item)
    }
}

object ArticleDiffCallback : DiffUtil.ItemCallback<WanArticleResponse>() {
    override fun areItemsTheSame(
        oldItem: WanArticleResponse,
        newItem: WanArticleResponse
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: WanArticleResponse,
        newItem: WanArticleResponse
    ): Boolean {
        return oldItem == newItem
    }

}