package com.ke.wanandroid.common.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ke.wanandroid.api.response.WanTopicResponse
import com.ke.wanandroid.common.R
import com.ke.wanandroid.common.databinding.ItemCategoryChipBinding
import com.ke.wanandroid.common.databinding.ItemCategoryTitleBinding


class CategoryAdapter(
    private val list: List<WanTopicResponse>,
    private val onClickListener: (WanTopicResponse) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == R.layout.item_category_title) {
            return SystemTitleViewHolder(
                ItemCategoryTitleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            return SystemChipViewHolder(
                ItemCategoryChipBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val topic = list[position]
        if (getItemViewType(position) == R.layout.item_category_title) {
            (holder as? SystemTitleViewHolder)?.bind(topic.name)
        } else {
            (holder as? SystemChipViewHolder)?.apply {
                bind(topic)
                binding.chip.setOnClickListener {
                    onClickListener(topic)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].children.isEmpty()) R.layout.item_category_chip else R.layout.item_category_title
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class SystemTitleViewHolder(private val binding: ItemCategoryTitleBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(title: String) {
        binding.title.text = title
    }

}

class SystemChipViewHolder(internal val binding: ItemCategoryChipBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(topicResponse: WanTopicResponse) {
        binding.chip.text = topicResponse.name

    }
}