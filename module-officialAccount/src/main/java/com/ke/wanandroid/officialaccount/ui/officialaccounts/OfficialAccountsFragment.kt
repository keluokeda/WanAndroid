package com.ke.wanandroid.officialaccount.ui.officialaccounts


import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.appbar.MaterialToolbar
import com.ke.wanandroid.common.const.ExtraKey
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.common.db.Topic
import com.ke.wanandroid.common.ui.tab.BaseTabFragment
import com.ke.wanandroid.officialaccount.R
import com.ke.wanandroid.officialaccount.ui.article.ArticleListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OfficialAccountsFragment :
    BaseTabFragment() {

    override val viewModel: OfficialAccountsViewModel by viewModels()
    override fun createArticleListFragment(topic: Topic): Fragment {
        return ArticleListFragment.createInstance(topic.id)
    }


    override fun initToolbar(
        toolbar: MaterialToolbar,
        list: List<Topic>,
        viewPager: ViewPager2
    ) {
        toolbar.title = "公众号"
        toolbar.menu.clear()
        toolbar.inflateMenu(R.menu.official_accounts_menu_search_and_sort)
        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_search) {
                val id = list[viewPager.currentItem].id
                ARouter.getInstance().build(PagePath.OFFICIAL_ACCOUNTS_SEARCH).withInt("id", id)
                    .navigation()
            } else if (it.itemId == R.id.action_sort) {
                ARouter.getInstance().build(PagePath.TOPIC_SORT)
                    .withInt(ExtraKey.TYPE, Topic.TYPE_OFFICIAL_ACCOUNTS)
                    .navigation()
            }
            true
        }
    }

}