package com.ke.wanandroid.common.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.module.LoadMoreModule
import com.ke.mvvm.base.ui.BaseViewBindingAdapter
import com.ke.mvvm.base.ui.ViewBindingViewHolder
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.databinding.ItemArticleBinding

class ArticleListAdapter(
    private val converter: (
        holder: ViewBindingViewHolder<ItemArticleBinding>,
        item: WanArticleResponse
    ) -> Unit
) :
    BaseViewBindingAdapter<WanArticleResponse, ItemArticleBinding>(),LoadMoreModule {
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