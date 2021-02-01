package com.ke.wanandroid.ui.sort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.listener.OnItemDragListener
import com.chad.library.adapter.base.module.DraggableModule
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseFragment
import com.ke.mvvm.base.ui.BaseViewBindingAdapter
import com.ke.mvvm.base.ui.ViewBindingViewHolder
import com.ke.wanandroid.common.R
import com.ke.wanandroid.common.databinding.LayoutBaseListBinding
import com.ke.wanandroid.common.db.Topic
import com.ke.wanandroid.common.log
import com.ke.wanandroid.databinding.ItemTopicSortBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicSortFragment : BaseFragment(R.layout.layout_base_list) {
    private val binding: LayoutBaseListBinding by viewbind()
    private val topicSortViewModel: TopicSortViewModel by viewModels()
    private val adapter =
        object : BaseViewBindingAdapter<Topic, ItemTopicSortBinding>(), DraggableModule {
            override fun createViewBinding(
                inflater: LayoutInflater,
                parent: ViewGroup
            ): ItemTopicSortBinding {
                return ItemTopicSortBinding.inflate(inflater, parent, false)
            }

            override fun convert(holder: ViewBindingViewHolder<ItemTopicSortBinding>, item: Topic) {
                holder.viewBinding.checkBox.text = item.name
                holder.viewBinding.checkBox.setOnCheckedChangeListener(null)
                holder.viewBinding.checkBox.isChecked = item.enable
                holder.viewBinding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                    topicSortViewModel.update(item.updateEnable(isChecked))
                }
            }

        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.swipeRefreshLayout.isEnabled = false
        binding.toolbar.title = "排序"
        adapter.draggableModule.isDragEnabled = true
        adapter.draggableModule.setOnItemDragListener(object : OnItemDragListener {
            override fun onItemDragStart(viewHolder: RecyclerView.ViewHolder?, pos: Int) {

            }

            override fun onItemDragMoving(
                source: RecyclerView.ViewHolder?,
                from: Int,
                target: RecyclerView.ViewHolder?,
                to: Int
            ) {

            }

            override fun onItemDragEnd(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
                val resultList = adapter.data

                "拖动完成 $pos $resultList ".log()

                topicSortViewModel.resortList(resultList)
            }

        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        topicSortViewModel.topicList.observe(viewLifecycleOwner) {
            adapter.setList(it)
        }
    }
}