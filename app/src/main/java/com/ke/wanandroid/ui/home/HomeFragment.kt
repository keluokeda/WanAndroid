package com.ke.wanandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.hi.dhl.binding.viewbind
import com.ke.wanandroid.R
import com.ke.wanandroid.api.response.WanBannerResponse
import com.ke.wanandroid.common.const.ExtraKey
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.common.ui.BaseArticleListFragment
import com.ke.wanandroid.common.ui.BaseArticleListViewModel
import com.ke.wanandroid.databinding.FragmentHomeBinding
import com.ke.wanandroid.databinding.LayoutBannerBinding
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
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
            if (adapter.headerLayoutCount == 0) {
                val header = LayoutBannerBinding.inflate(LayoutInflater.from(requireContext()))
                header.banner.setAdapter(object : BannerImageAdapter<WanBannerResponse>(it) {
                    override fun onBindView(
                        holder: BannerImageHolder,
                        data: WanBannerResponse,
                        position: Int,
                        size: Int
                    ) {
                        Glide.with(this@HomeFragment)
                            .load(data.imagePath)
                            .into(holder.imageView)
                    }

                })
                    .addBannerLifecycleObserver(this)
                    .setIndicator(CircleIndicator(requireContext()))
                    .setOnBannerListener { _, position ->
                        ARouter.getInstance().build(PagePath.H5_DEFAULT)
                            .withString(ExtraKey.URL, it[position].url)
                            .navigation()
                    }

                adapter.addHeaderView(header.root)
            }
        }
        setupRetry(binding.retry, binding.recyclerView, homeViewModel)


        setupAdapter(
            binding.swipeRefreshLayout,
            homeViewModel,
            adapter,
            binding.recyclerView
        )
    }
}