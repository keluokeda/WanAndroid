package com.ke.wanandroid.common.ui.tab

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayoutMediator
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseFragment
import com.ke.mvvm.base.ui.FragmentViewPager2Adapter
import com.ke.wanandroid.api.response.WanTopicResponse
import com.ke.wanandroid.common.R
import com.ke.wanandroid.common.databinding.FragmentBaseTabBinding

abstract class BaseTabFragment : BaseFragment(R.layout.fragment_base_tab) {

    private val binding: FragmentBaseTabBinding by viewbind()

    protected abstract val viewModel: BaseTabViewModel


    abstract fun createArticleListFragment(wanTopicResponse: WanTopicResponse): Fragment

    abstract fun initToolbar(
        toolbar: MaterialToolbar,
        list: List<WanTopicResponse>,
        viewPager: ViewPager2
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadingViewVisible.observe(viewLifecycleOwner) {
            binding.loading.isVisible = it
        }
        viewModel.contentViewVisible.observe(viewLifecycleOwner) {
            binding.tabLayout.isVisible = it
            binding.viewPager.isVisible = it
        }
        viewModel.retryViewVisible.observe(viewLifecycleOwner) {
            binding.retry.isVisible = it
        }
        binding.retry.setOnClickListener {
            viewModel.loadData()
        }
        viewModel.topicList.observe(viewLifecycleOwner) { list ->
            binding.viewPager.adapter =
                FragmentViewPager2Adapter(
                    this,
                    list.map { createArticleListFragment(it) }
                )
            binding.viewPager.offscreenPageLimit = list.size
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.text = list[position].name
            }.attach()
            initToolbar(binding.toolbar, list, binding.viewPager)
//            binding.toolbar.inflateMenu(R.menu.official_accounts_menu_search_and_sort)
//
//            binding.toolbar.setOnMenuItemClickListener {
//                if (it.itemId == R.id.action_search) {
//                    val id = list[binding.viewPager.currentItem].id
//                    ARouter.getInstance().build(PagePath.OFFICIAL_ACCOUNTS_SEARCH).withInt("id", id)
//                        .navigation()
//                }
//                return@setOnMenuItemClickListener true
//            }
        }

    }
}