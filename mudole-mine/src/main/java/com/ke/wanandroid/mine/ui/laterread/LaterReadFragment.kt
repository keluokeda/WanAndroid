package com.ke.wanandroid.mine.ui.laterread

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseFragment
import com.ke.mvvm.base.ui.BaseViewBindingAdapter
import com.ke.mvvm.base.ui.ViewBindingViewHolder
import com.ke.wanandroid.common.R
import com.ke.wanandroid.common.bindArticle
import com.ke.wanandroid.common.const.ExtraKey
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.common.databinding.ItemArticleBinding
import com.ke.wanandroid.common.databinding.LayoutBaseListBinding
import com.ke.wanandroid.common.db.LaterRead
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaterReadFragment : BaseFragment(R.layout.layout_base_list) {

    private val binding: LayoutBaseListBinding by viewbind()

    private val viewModel: LaterReadViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = object : BaseViewBindingAdapter<LaterRead, ItemArticleBinding>() {
            override fun createViewBinding(
                inflater: LayoutInflater,
                parent: ViewGroup
            ): ItemArticleBinding {
                return ItemArticleBinding.inflate(inflater, parent, false)
            }

            override fun convert(
                holder: ViewBindingViewHolder<ItemArticleBinding>,
                item: LaterRead
            ) {
                holder.viewBinding.bindArticle(
                    item.toArticle(),
                    Glide.with(this@LaterReadFragment)
                )
            }

        }

        binding.swipeRefreshLayout.isEnabled = false

        adapter.setDiffCallback(object : DiffUtil.ItemCallback<LaterRead>() {
            override fun areItemsTheSame(oldItem: LaterRead, newItem: LaterRead): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: LaterRead,
                newItem: LaterRead
            ): Boolean {
                return oldItem == newItem
            }

        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.toolbar.title = "稍后阅读"
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.toolbar.menu.apply {
            clear()
            add(0, 1, 0, "清空").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        }
        binding.toolbar.setOnMenuItemClickListener {
            viewModel.deleteAll()
            true
        }
        viewModel.dataList.observe(viewLifecycleOwner) {
            adapter.setDiffNewData(it.toMutableList())
        }

        adapter.setEmptyView(com.ke.wanandroid.mine.R.layout.layout_list_empty)
        adapter.setOnItemClickListener { _, _, position ->
            ARouter.getInstance().build(PagePath.H5_ARTICLE)
                .withParcelable(ExtraKey.ARTICLE, adapter.getItem(position).toArticle())
                .navigation()
        }
        adapter.addChildClickViewIds(com.ke.wanandroid.mine.R.id.action)
        adapter.setOnItemChildClickListener { _, target, position ->
            val laterRead = adapter.getItem(position)

            val popupMenu = PopupMenu(target.context, target)
            popupMenu.menu.add(0, 1, 0, "删除")

            popupMenu.setOnMenuItemClickListener {
                viewModel.delete(laterRead)

                true
            }
            popupMenu.show()
        }

        setupSnackbar(viewModel)
    }


}