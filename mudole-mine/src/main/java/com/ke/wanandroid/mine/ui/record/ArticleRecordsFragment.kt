package com.ke.wanandroid.mine.ui.record

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseFragment
import com.ke.wanandroid.common.bindArticle
import com.ke.wanandroid.common.const.ExtraKey
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.common.databinding.LayoutBaseListBinding
import com.ke.wanandroid.common.ui.ArticleListAdapter
import com.ke.wanandroid.mine.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleRecordsFragment : BaseFragment(com.ke.wanandroid.common.R.layout.layout_base_list) {

    private val binding: LayoutBaseListBinding by viewbind()

    private val viewModel: ArticleRecordsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArticleListAdapter { holder, article ->
            holder.viewBinding.bindArticle(article, Glide.with(this))
        }
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
            viewModel.deleteAll()
            true
        }
        viewModel.articles.observe(viewLifecycleOwner) {
//            adapter.setList()
            adapter.setList(it)
        }

        adapter.setEmptyView(R.layout.layout_list_empty)
        adapter.setOnItemClickListener { _, _, position ->
            ARouter.getInstance().build(PagePath.H5_ARTICLE)
                .withParcelable(ExtraKey.ARTICLE, adapter.getItem(position)).navigation()
        }
        adapter.addChildClickViewIds(R.id.action)
        adapter.setOnItemChildClickListener { _, view, position ->
            val popupMenu = PopupMenu(view.context, view)
            popupMenu.menu.add(0, 1, 0, "删除")
            popupMenu.setOnMenuItemClickListener {
                val article = adapter.getItem(position)
                viewModel.delete(article)
                true
            }
            popupMenu.show()
        }

    }
}