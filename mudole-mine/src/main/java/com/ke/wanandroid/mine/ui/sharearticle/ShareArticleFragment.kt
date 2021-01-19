package com.ke.wanandroid.mine.ui.sharearticle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseFragment
import com.ke.wanandroid.mine.R
import com.ke.wanandroid.mine.databinding.MineFragmentShareArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShareArticleFragment : BaseFragment(R.layout.mine_fragment_share_article) {

    private val binding: MineFragmentShareArticleBinding by viewbind()
    private val shareArticleViewModel: ShareArticleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shareArticleViewModel.loadingViewVisible.observe(viewLifecycleOwner) {
            binding.link.isEnabled = !it
            binding.title.isEnabled = !it
            binding.share.isEnabled = !it
            binding.loadingView.isVisible = it
        }

        shareArticleViewModel.shareResult.observe(viewLifecycleOwner) {
            onBackPressed()
        }
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.share.setOnClickListener {
            val title = binding.title.text?.toString() ?: return@setOnClickListener
            val link = binding.link.text?.toString() ?: return@setOnClickListener
            shareArticleViewModel.shareArticle(title, link)
        }
    }
}