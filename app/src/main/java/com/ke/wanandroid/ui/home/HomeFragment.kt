package com.ke.wanandroid.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.hi.dhl.binding.viewbind
import com.ke.wanandroid.R
import com.ke.wanandroid.common.ui.BaseArticleListFragment
import com.ke.wanandroid.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseArticleListFragment(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()

    private val binding: FragmentHomeBinding by viewbind()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        homeViewModel.retryViewVisible.observe(viewLifecycleOwner) {
            binding.recyclerView.isVisible = !it
            binding.retry.isVisible = it
        }
        binding.retry.setOnClickListener {
            homeViewModel.retry()
        }
//        val adapter = HomeArticleAdapter(Glide.with(this))
        adapter.setOnItemClickListener { _, _, position -> }

        homeViewModel.bannerData.observe(viewLifecycleOwner) {
            if (adapter.headerLayoutCount == 0) {


            }
        }
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        setup(
            binding.swipeRefreshLayout,
            homeViewModel,
            adapter,
            binding.recyclerView
        )


//        binding.toolBar.menu.add(0, 0, 0, "搜索").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
    }
}