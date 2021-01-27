package com.ke.wanandroid.h5

import android.os.Bundle
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.const.ExtraKey
import com.ke.wanandroid.common.const.PagePath
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = PagePath.H5_ARTICLE)
class ArticleWebViewActivity : WebViewActivity() {


    private val articleWebViewViewModel: ArticleWebViewViewModel by viewModels()


    @Autowired(name = ExtraKey.ARTICLE)
    lateinit var wanArticleResponse: WanArticleResponse


    override val url: String
        get() = wanArticleResponse.link

    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)

        articleWebViewViewModel.insertRecord(wanArticleResponse)

    }
}