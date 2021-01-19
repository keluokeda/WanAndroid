package com.ke.wanandroid.mine.ui.sharearticle

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.ke.mvvm.base.ui.BaseFragmentActivity
import com.ke.wanandroid.common.const.PagePath
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = PagePath.SHARE_ARTICLE)
class ShareArticleActivity : BaseFragmentActivity() {
    override val fragment: Fragment
        get() = ShareArticleFragment()
}