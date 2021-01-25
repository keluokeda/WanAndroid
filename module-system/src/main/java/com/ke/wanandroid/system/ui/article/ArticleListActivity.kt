package com.ke.wanandroid.system.ui.article

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ke.mvvm.base.ui.BaseActivity
import com.ke.wanandroid.api.response.WanTopicResponse
import com.ke.wanandroid.common.R
import com.ke.wanandroid.common.const.ExtraKey
import com.ke.wanandroid.common.const.PagePath
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = PagePath.SYSTEM_ARTICLE_LIST)
class ArticleListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_fragment_container)

        val topic = intent.getParcelableExtra<WanTopicResponse>(ExtraKey.TOPIC)
            ?: throw RuntimeException("需要传入 topic")

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.fragment_container,
                ArticleListFragment.instance(topic)
            ).commit()
        }
    }


}