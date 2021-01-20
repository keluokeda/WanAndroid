package com.ke.wanandroid.ui.project

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.MaterialToolbar
import com.ke.wanandroid.api.response.WanTopicResponse
import com.ke.wanandroid.common.ui.tab.BaseTabFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectFragment : BaseTabFragment() {
    override val viewModel: ProjectViewModel by viewModels()
    override fun createArticleListFragment(wanTopicResponse: WanTopicResponse): Fragment {
        return ProjectArticlesFragment.createInstance(wanTopicResponse.id)
    }

    override fun initToolbar(
        toolbar: MaterialToolbar,
        list: List<WanTopicResponse>,
        viewPager: ViewPager2
    ) {
        toolbar.title = "项目"
    }

}