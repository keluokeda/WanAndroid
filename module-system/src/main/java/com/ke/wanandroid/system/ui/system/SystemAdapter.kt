package com.ke.wanandroid.system.ui.system

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.ke.wanandroid.api.response.WanTopicResponse
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.system.R
import com.ke.wanandroid.system.databinding.SystemItemChipBinding
import com.ke.wanandroid.system.databinding.SystemItemTitleBinding
import com.ke.wanandroid.system.ui.article.ArticleListActivity

class SystemAdapter(private val list: List<WanTopicResponse>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == R.layout.system_item_title) {
            return SystemTitleViewHolder(
                SystemItemTitleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            return SystemChipViewHolder(
                SystemItemChipBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val topic = list[position]
        if (getItemViewType(position) == R.layout.system_item_title) {
            (holder as? SystemTitleViewHolder)?.bind(topic.name)
        } else {
            (holder as? SystemChipViewHolder)?.bind(topic)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].children.isEmpty()) R.layout.system_item_chip else R.layout.system_item_title
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class SystemTitleViewHolder(private val binding: SystemItemTitleBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(title: String) {
        binding.title.text = title
    }

}

class SystemChipViewHolder(private val binding: SystemItemChipBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(topicResponse: WanTopicResponse) {
        binding.chip.text = topicResponse.name
        binding.chip.setOnClickListener {
            ARouter.getInstance().build(PagePath.SYSTEM_ARTICLE_LIST)
                .withParcelable(ArticleListActivity.EXTRA_TOPIC, topicResponse).navigation()
        }
    }
}