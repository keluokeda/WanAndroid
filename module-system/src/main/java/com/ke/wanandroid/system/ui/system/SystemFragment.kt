package com.ke.wanandroid.system.ui.system

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxItemDecoration
import com.google.android.flexbox.FlexboxLayoutManager
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseFragment
import com.ke.wanandroid.system.R
import com.ke.wanandroid.system.databinding.SystemFragmentSystemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SystemFragment : BaseFragment(R.layout.system_fragment_system) {


    private val viewModel: SystemViewModel by viewModels()
    private val binding: SystemFragmentSystemBinding by viewbind()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = FlexboxLayoutManager(requireContext(),FlexDirection.ROW)

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.addItemDecoration(FlexboxItemDecoration(requireContext()).apply {
            setDrawable(requireActivity().getDrawable(R.drawable.divider_empty_margin_small))
            setOrientation(FlexboxItemDecoration.VERTICAL)        })

        viewModel.loadingViewVisible.observe(viewLifecycleOwner) {
            binding.loadingView.isVisible = it
        }

        viewModel.topicList.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter = SystemAdapter(it)
//            binding.recyclerView.setHasFixedSize(true)

        }
    }
}