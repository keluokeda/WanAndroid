package com.ke.wanandroid.ui.home

import com.bumptech.glide.RequestManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ke.wanandroid.R
import com.ke.wanandroid.api.response.WanArticleResponse

class HomeArticleAdapter(private val requestManager: RequestManager) :
    BaseQuickAdapter<WanArticleResponse, BaseViewHolder>(R.layout.item_article) {
    override fun convert(holder: BaseViewHolder, item: WanArticleResponse) {
        holder.apply {
            setGone(R.id.is_new, !item.fresh)
            setText(R.id.author, if(item.author.isNotEmpty()) item.author else item.shareUser)
            if (item.tags.isNotEmpty()) {
                setVisible(R.id.tag, true)
                setText(R.id.tag, item.tags.first().name)
            } else {
                setGone(R.id.tag, true)
            }
            if (item.envelopePic.isNotEmpty()) {
                setVisible(R.id.image, true)
                requestManager.load(item.envelopePic).into(getView(R.id.image))
            } else {
                setGone(R.id.image, true)
            }
            setText(R.id.title, item.title)
            setGone(R.id.desc, item.desc.isEmpty())
            setText(R.id.desc, item.desc)
            setText(R.id.chapter, item.superChapterName + ":" + item.chapterName)
            setText(R.id.time,item.niceShareDate)
        }
    }
}