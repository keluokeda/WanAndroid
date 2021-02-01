package com.ke.wanandroid.ui.project

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.appbar.MaterialToolbar
import com.ke.wanandroid.common.R
import com.ke.wanandroid.common.const.ExtraKey
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.common.db.Topic
import com.ke.wanandroid.common.ui.tab.BaseTabFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectFragment : BaseTabFragment() {
    override val viewModel: ProjectViewModel by viewModels()
    override fun createArticleListFragment(topic: Topic): Fragment {
        return ProjectArticlesFragment.createInstance(topic.id)
    }

    override fun initToolbar(toolbar: MaterialToolbar, list: List<Topic>, viewPager: ViewPager2) {
        toolbar.title = "项目"
        toolbar.menu.clear()
        toolbar.menu.add(0, 1, 0, "排序").setIcon(R.drawable.baseline_sort_toolbar_24dp)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

        toolbar.setOnMenuItemClickListener {
            ARouter.getInstance().build(PagePath.TOPIC_SORT)
                .withInt(ExtraKey.TYPE, Topic.TYPE_PROJECT)
                .navigation()
            return@setOnMenuItemClickListener true
        }
    }


}