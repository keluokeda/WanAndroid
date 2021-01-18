package com.ke.wanandroid.mine.ui.sharedartiles

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.ke.mvvm.base.ui.BaseFragmentActivity
import com.ke.wanandroid.common.const.ExtraKey
import com.ke.wanandroid.common.const.PagePath
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = PagePath.USER_SHARED_ARTICLES)
class UserSharedArticlesActivity : BaseFragmentActivity() {
    override val fragment: Fragment
        get() = UserSharedArticlesFragment().apply {
            arguments = bundleOf(ExtraKey.USER_ID to intent.getIntExtra(ExtraKey.USER_ID, 0))
        }
}