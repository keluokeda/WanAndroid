package com.ke.wanandroid.ui.navigation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.ke.wanandroid.common.ui.category.BaseCategoryFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationFragment : BaseCategoryFragment() {
    override val baseCategoryViewModel: NavigationViewModel by viewModels()

    private var isFirstResume = true

    override fun onResume() {
        super.onResume()
        if (isFirstResume) {
            isFirstResume = false
            baseCategoryViewModel.start()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.isVisible = false
    }

}