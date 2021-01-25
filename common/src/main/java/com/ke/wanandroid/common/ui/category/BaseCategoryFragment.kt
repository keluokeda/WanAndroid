package com.ke.wanandroid.common.ui.category

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxItemDecoration
import com.google.android.flexbox.FlexboxLayoutManager
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseFragment
import com.ke.wanandroid.api.response.WanTopicResponse
import com.ke.wanandroid.common.R
import com.ke.wanandroid.common.databinding.LayoutToolbarListLoadingRetryBinding

abstract class BaseCategoryFragment : BaseFragment(R.layout.layout_toolbar_list_loading_retry) {

    abstract val baseCategoryViewModel: BaseCategoryViewModel

    protected val binding: LayoutToolbarListLoadingRetryBinding by viewbind()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = FlexboxLayoutManager(requireContext(), FlexDirection.ROW)

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.addItemDecoration(FlexboxItemDecoration(requireContext()).apply {
            setDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.divider_empty_margin_small
                )
            )
            setOrientation(FlexboxItemDecoration.VERTICAL)
        })

        baseCategoryViewModel.topicList.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter = CategoryAdapter(it) { topic ->
                onTopicClick(topic)
            }
        }
        setupRetryAndLoading(
            binding.retry,
            binding.loadingView,
            binding.recyclerView,
            baseCategoryViewModel
        )

    }


    protected open fun onTopicClick(wanTopicResponse: WanTopicResponse) {

    }
}