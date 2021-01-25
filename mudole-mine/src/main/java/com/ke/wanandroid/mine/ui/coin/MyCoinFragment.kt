package com.ke.wanandroid.mine.ui.coin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.chad.library.adapter.base.module.LoadMoreModule
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseViewBindingAdapter
import com.ke.mvvm.base.ui.ViewBindingViewHolder
import com.ke.wanandroid.api.response.WanCoinResponse
import com.ke.wanandroid.common.ui.BaseDataListFragment
import com.ke.wanandroid.mine.R
import com.ke.wanandroid.mine.databinding.MineFragmentMyCoinBinding
import com.ke.wanandroid.mine.databinding.MineItemCoinBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyCoinFragment : BaseDataListFragment(R.layout.mine_fragment_my_coin) {

    private val adapter =
        object : BaseViewBindingAdapter<WanCoinResponse, MineItemCoinBinding>(), LoadMoreModule {
            override fun createViewBinding(
                inflater: LayoutInflater,
                parent: ViewGroup
            ): MineItemCoinBinding {
                return MineItemCoinBinding.inflate(layoutInflater, parent, false)
            }

            override fun convert(
                holder: ViewBindingViewHolder<MineItemCoinBinding>,
                item: WanCoinResponse
            ) {
                holder.viewBinding.score.text = "+" + item.coinCount
                val array = item.desc.split(",")
                holder.viewBinding.title.text = array[0].trim()
                holder.viewBinding.time.text = array[1].trim()
            }

        }

    private val viewModel: MyCoinViewModel by viewModels()

    private val binding: MineFragmentMyCoinBinding by viewbind()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRefreshAndLoadMore(
            binding.swipeRefreshLayout,
            viewModel,
            adapter,
            binding.recyclerView
        )

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

//        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))

        viewModel.coinCount.observe(viewLifecycleOwner) {

            if (adapter.headerLayoutCount > 0) {
                return@observe
            }
            val parent = View.inflate(requireContext(), R.layout.mine_coin_header, null)
            parent.findViewById<TextView>(R.id.count).text = it
            adapter.addHeaderView(parent)
        }
    }
}