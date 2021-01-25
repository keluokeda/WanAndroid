package com.ke.wanandroid.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.hi.dhl.binding.viewbind
import com.ke.wanandroid.R
import com.ke.wanandroid.common.ui.BaseArticleListFragment
import com.ke.wanandroid.common.ui.BaseArticleListViewModel
import com.ke.wanandroid.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseArticleListFragment(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()

    private val binding: FragmentHomeBinding by viewbind()
    override val baseArticleListViewModel: BaseArticleListViewModel<*>
        get() = homeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        homeViewModel.bannerData.observe(viewLifecycleOwner) {

        }
        setupRetry(binding.retry, binding.recyclerView, homeViewModel)


        setRefreshAndLoadMore(
            binding.swipeRefreshLayout,
            homeViewModel,
            adapter,
            binding.recyclerView
        )
    }
}