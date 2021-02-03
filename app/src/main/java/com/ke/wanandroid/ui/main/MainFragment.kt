package com.ke.wanandroid.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseFragment
import com.ke.mvvm.base.ui.FragmentViewPager2Adapter
import com.ke.wanandroid.common.R
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.common.databinding.FragmentBaseTabBinding
import com.ke.wanandroid.ui.answer.AnswerFragment
import com.ke.wanandroid.ui.home.HomeFragment
import com.ke.wanandroid.ui.navigation.NavigationFragment
import com.ke.wanandroid.ui.square.SquareFragment

class MainFragment : BaseFragment(R.layout.fragment_base_tab) {

    private val binding: FragmentBaseTabBinding by viewbind()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.apply {
            title = "首页"
            menu.clear()
            menu.add(0, 1, 0, "搜索").setIcon(R.drawable.baseline_search_toolbar_24dp)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
            setOnMenuItemClickListener {
                ARouter.getInstance().build(PagePath.APP_SEARCH).navigation()
                true
            }
        }

        binding.tabLayout.tabMode = TabLayout.MODE_FIXED
        val titles = listOf("首页", "广场", "问答", "导航")

        binding.viewPager.adapter =
            FragmentViewPager2Adapter(
                this,
                listOf(HomeFragment(), SquareFragment(), AnswerFragment(), NavigationFragment())
            )
        binding.viewPager.offscreenPageLimit = titles.size
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }
}