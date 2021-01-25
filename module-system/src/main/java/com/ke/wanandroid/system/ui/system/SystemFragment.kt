package com.ke.wanandroid.system.ui.system

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.launcher.ARouter
import com.ke.wanandroid.api.response.WanTopicResponse
import com.ke.wanandroid.common.const.ExtraKey
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.common.ui.category.BaseCategoryFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SystemFragment : BaseCategoryFragment() {


    override val baseCategoryViewModel: SystemViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.title = "体系"
    }

    override fun onTopicClick(wanTopicResponse: WanTopicResponse) {
        ARouter.getInstance().build(PagePath.SYSTEM_ARTICLE_LIST)
            .withParcelable(ExtraKey.TOPIC, wanTopicResponse)
            .navigation()
    }
}