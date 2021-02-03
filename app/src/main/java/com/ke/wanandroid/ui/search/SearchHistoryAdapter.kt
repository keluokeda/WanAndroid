package com.ke.wanandroid.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ke.wanandroid.R
import com.ke.wanandroid.api.response.WanHotKeyResponse
import com.ke.wanandroid.common.db.SearchHistory
import com.ke.wanandroid.databinding.ItemSearchHistoryBinding
import com.ke.wanandroid.databinding.ItemSearchHistoryHeaderBinding
import com.ke.wanandroid.databinding.ItemSearchHotKeyBinding
import com.ke.wanandroid.databinding.ItemSearchHotKeyHeaderBinding
import com.ke.wanandroid.model.SearchItem

class SearchHistoryAdapter(
    private val list: MutableList<SearchItem> = mutableListOf(),
    private val onClickListener: (String) -> Unit,
    private val onDeleteButtonClickListener: (SearchHistory) -> Unit,
    private val onDeleteAllButtonClickListener: () -> Unit
) :
    ListAdapter<SearchItem, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<SearchItem>() {
        override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem == newItem
        }

    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_search_hot_key_header -> SearchHotKeyHeaderViewHolder(
                ItemSearchHotKeyHeaderBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            R.layout.item_search_hot_key -> SearchHotKeyViewHolder(
                ItemSearchHotKeyBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            R.layout.item_search_history_header -> SearchHistoryHeaderViewHolder(
                ItemSearchHistoryHeaderBinding.inflate(layoutInflater, parent, false)
            )
            R.layout.item_search_history -> SearchHistoryViewHolder(
                ItemSearchHistoryBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("无效的 viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val searchItem = list[position]) {
            SearchItem.HotKeyHeader -> {
            }
            is SearchItem.HotKey -> {
                (holder as? SearchHotKeyViewHolder)?.apply {
                    itemSearchHotKeyBinding.chip.text = searchItem.wanHotKeyResponse.name
                    itemSearchHotKeyBinding.chip.setOnClickListener {
                        onClickListener(searchItem.wanHotKeyResponse.name)
                    }
                }
            }
            SearchItem.HistoryHeader -> {
                (holder as? SearchHistoryHeaderViewHolder)?.apply {
                    itemSearchHistoryHeaderBinding.deleteAll.setOnClickListener {
                        onDeleteAllButtonClickListener()
                    }
                }
            }
            is SearchItem.History -> {

                (holder as? SearchHistoryViewHolder)?.apply {
                    itemSearchHistoryBinding.name.text = searchItem.history.keyword
                    itemSearchHistoryBinding.delete.setOnClickListener {
                        onDeleteButtonClickListener(searchItem.history)
                    }
                    itemSearchHistoryBinding.root.setOnClickListener {
                        onClickListener(searchItem.history.keyword)
                    }
                }
            }
        }
    }

    fun setHotKeyList(hotKeyList: List<WanHotKeyResponse>) {
        if (hotKeyList.isEmpty()) {
            return
        }

        //移除所有hot key相关的元素
        list.removeAll { it is SearchItem.HotKey || it is SearchItem.HotKeyHeader }
        val result = mutableListOf<SearchItem>()
        result.add(SearchItem.HotKeyHeader)
        result.addAll(hotKeyList.map { SearchItem.HotKey(it) })

        list.addAll(0, result)
//        submitList(list)
        notifyDataSetChanged()
    }

    fun setHistoryList(historyList: List<SearchHistory>) {
        list.removeAll { it is SearchItem.History || it is SearchItem.HistoryHeader }

        if (historyList.isEmpty()) {
            notifyDataSetChanged()
//            submitList(list)
            return
        }
        val result = mutableListOf<SearchItem>()
        result.add(SearchItem.HistoryHeader)
        result.addAll(historyList.map { SearchItem.History(it) })

        list.addAll(result)
        notifyDataSetChanged()
//        submitList(list)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            SearchItem.HotKeyHeader -> R.layout.item_search_hot_key_header
            is SearchItem.HotKey -> R.layout.item_search_hot_key
            SearchItem.HistoryHeader -> R.layout.item_search_history_header
            is SearchItem.History -> R.layout.item_search_history
        }
    }
}

private class SearchHotKeyHeaderViewHolder(itemSearchHotKeyHeaderBinding: ItemSearchHotKeyHeaderBinding) :
    RecyclerView.ViewHolder(itemSearchHotKeyHeaderBinding.root)

private class SearchHotKeyViewHolder(internal val itemSearchHotKeyBinding: ItemSearchHotKeyBinding) :
    RecyclerView.ViewHolder(itemSearchHotKeyBinding.root)

internal class SearchHistoryHeaderViewHolder(internal val itemSearchHistoryHeaderBinding: ItemSearchHistoryHeaderBinding) :
    RecyclerView.ViewHolder(itemSearchHistoryHeaderBinding.root)

internal class SearchHistoryViewHolder(internal val itemSearchHistoryBinding: ItemSearchHistoryBinding) :
    RecyclerView.ViewHolder(itemSearchHistoryBinding.root)