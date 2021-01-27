package com.ke.wanandroid.mine.ui.record

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
import com.ke.wanandroid.common.bindArticle
import com.ke.wanandroid.common.const.ExtraKey
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.common.databinding.ItemArticleBinding
import com.ke.wanandroid.common.databinding.LayoutBaseListBinding
import com.ke.wanandroid.common.db.ArticleRecord
import com.ke.wanandroid.mine.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleRecordsFragment : BaseFragment(R.layout.layout_base_list) {

    private val binding: LayoutBaseListBinding by viewbind()

    private val articleRecordsViewModel: ArticleRecordsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = object : BaseViewBindingAdapter<ArticleRecord, ItemArticleBinding>() {
            override fun createViewBinding(
                inflater: LayoutInflater,
                parent: ViewGroup
            ): ItemArticleBinding {
                return ItemArticleBinding.inflate(inflater, parent, false)
            }

            override fun convert(
                holder: ViewBindingViewHolder<ItemArticleBinding>,
                item: ArticleRecord
            ) {
                holder.viewBinding.bindArticle(
                    item.toArticle(),
                    Glide.with(this@ArticleRecordsFragment)
                )
            }

        }

        binding.swipeRefreshLayout.isEnabled = false

        adapter.setDiffCallback(object : DiffUtil.ItemCallback<ArticleRecord>() {
            override fun areItemsTheSame(oldItem: ArticleRecord, newItem: ArticleRecord): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ArticleRecord,
                newItem: ArticleRecord
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
        binding.toolbar.title = "历史记录"
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.toolbar.menu.apply {
            clear()
            add(0, 1, 0, "清空").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        }
        binding.toolbar.setOnMenuItemClickListener {
            articleRecordsViewModel.deleteAll()
            true
        }
        articleRecordsViewModel.dataList.observe(viewLifecycleOwner) {
            adapter.setDiffNewData(it.toMutableList())
        }

        adapter.setEmptyView(R.layout.layout_list_empty)
        adapter.setOnItemClickListener { _, _, position ->
            ARouter.getInstance().build(PagePath.H5_ARTICLE)
                .withParcelable(ExtraKey.ARTICLE, adapter.getItem(position).toArticle())
                .navigation()
        }
        adapter.addChildClickViewIds(R.id.action)
        adapter.setOnItemChildClickListener { _, target, position ->
            val articleRecord = adapter.getItem(position)

            val popupMenu = PopupMenu(target.context, target)
            popupMenu.menu.add(0, 1, 0, "删除")
            if (articleRecord.collect) {
                popupMenu.menu.add(0, 2, 0, "取消收藏")
            } else {
                popupMenu.menu.add(0, 3, 0, "收藏")
            }
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    1 -> articleRecordsViewModel.delete(articleRecord)
                    2 -> articleRecordsViewModel.cancelCollectArticle(articleRecord)
                    3 -> articleRecordsViewModel.collectArticle(articleRecord)
                }
                true
            }
            popupMenu.show()
        }

        setupSnackbar(articleRecordsViewModel)
    }
}