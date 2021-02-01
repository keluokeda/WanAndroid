package com.ke.wanandroid.ui.sort

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.ke.mvvm.base.ui.BaseFragmentActivity
import com.ke.wanandroid.common.const.ExtraKey
import com.ke.wanandroid.common.const.PagePath
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = PagePath.TOPIC_SORT)
class TopicSortActivity : BaseFragmentActivity() {
    override val fragment: Fragment
        get() = TopicSortFragment().apply {
            arguments = bundleOf(ExtraKey.TYPE to intent.getIntExtra(ExtraKey.TYPE, 0))
        }
}