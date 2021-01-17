package com.ke.wanandroid.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseFragment
import com.ke.wanandroid.R
import com.ke.wanandroid.common.ui.BaseDataListFragment
import com.ke.wanandroid.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseDataListFragment(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()

    private val binding: FragmentHomeBinding by viewbind()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HomeArticleAdapter(Glide.with(this))
        adapter.setOnItemClickListener { _, _, position -> }
//        binding.recyclerView.adapter = adapter
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