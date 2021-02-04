package com.ke.wanandroid.mine.ui.coinrank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseViewBindingAdapter
import com.ke.mvvm.base.ui.ViewBindingViewHolder
import com.ke.wanandroid.api.response.WanUserInfoResponse
import com.ke.wanandroid.common.const.ExtraKey
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.common.ui.BaseDataListFragment
import com.ke.wanandroid.mine.R
import com.ke.wanandroid.mine.databinding.MineFragmentCoinRankBinding
import com.ke.wanandroid.mine.databinding.MineItemUserRankBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinRankFragment : BaseDataListFragment(R.layout.mine_fragment_coin_rank) {


    private val adapter =
        object : BaseViewBindingAdapter<WanUserInfoResponse, MineItemUserRankBinding>(),
            LoadMoreModule {
            override fun createViewBinding(
                inflater: LayoutInflater,
                parent: ViewGroup
            ): MineItemUserRankBinding {
                return MineItemUserRankBinding.inflate(inflater, parent, false)
            }

            override fun convert(
                holder: ViewBindingViewHolder<MineItemUserRankBinding>,
                item: WanUserInfoResponse
            ) {
                holder.viewBinding.apply {
                    username.text = item.username
                    rank.text = item.rank
                    coin.text = item.coinCount.toString()
                }
            }

        }

    private val binding: MineFragmentCoinRankBinding by viewbind()

    private val viewModel: CoinRankViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRefreshAndLoadMore(
            binding.swipeRefreshLayout,
            viewModel,
            adapter,
            binding.recyclerView
        )
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        adapter.setOnItemClickListener { _, _, position ->
            val userId = adapter.getItem(position).userId
            ARouter.getInstance().build(PagePath.USER_SHARED_ARTICLES)
                .withInt(ExtraKey.USER_ID, userId).navigation()
        }
    }
}