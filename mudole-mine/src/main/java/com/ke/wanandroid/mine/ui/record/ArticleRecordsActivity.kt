package com.ke.wanandroid.mine.ui.record

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.ke.mvvm.base.ui.BaseFragmentActivity
import com.ke.wanandroid.common.const.PagePath
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = PagePath.ARTICLE_RECORDS)
class ArticleRecordsActivity : BaseFragmentActivity() {
    override val fragment: Fragment
        get() = ArticleRecordsFragment()
}