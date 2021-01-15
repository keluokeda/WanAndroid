package com.ke.wanandroid.officialaccount.ui.officialaccounts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseFragment
import com.ke.mvvm.base.ui.FragmentViewPager2Adapter


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
        }
    }
}