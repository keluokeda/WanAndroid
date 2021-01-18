package com.ke.wanandroid.officialaccount.ui.officialaccounts


import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.tabs.TabLayoutMediator
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseFragment
import com.ke.mvvm.base.ui.FragmentViewPager2Adapter
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.officialaccount.R
import com.ke.wanandroid.officialaccount.databinding.OfficialAccountsFragmentOfficialAccountsBinding
import com.ke.wanandroid.officialaccount.ui.article.ArticleListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OfficialAccountsFragment :
    BaseFragment(R.layout.official_accounts_fragment_official_accounts) {

    private val binding: OfficialAccountsFragmentOfficialAccountsBinding by viewbind()
    private val viewModel: OfficialAccountsViewModel by viewModels()

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
                    list
                ) { t ->
                    ArticleListFragment.createInstance(t.id)
                }
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.text = list[position].name
            }.attach()

            binding.toolbar.setOnMenuItemClickListener {
                if (it.itemId == R.id.action_search) {
                    val id = list[binding.viewPager.currentItem].id
                    ARouter.getInstance().build(PagePath.OFFICIAL_ACCOUNTS_SEARCH).withInt("id", id)
                        .navigation()
                }
                return@setOnMenuItemClickListener true
            }
        }

    }
}