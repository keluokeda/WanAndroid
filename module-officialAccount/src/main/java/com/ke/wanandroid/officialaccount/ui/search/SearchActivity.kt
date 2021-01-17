package com.ke.wanandroid.officialaccount.ui.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.ke.mvvm.base.ui.BaseActivity
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.officialaccount.R
import com.ke.wanandroid.officialaccount.ui.article.ArticleListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = PagePath.OFFICIAL_ACCOUNTS_SEARCH)
class SearchActivity : BaseActivity() {


    //    private val binding: OfficialAccountsActivitySearchBinding by viewbind()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.official_accounts_activity_search)

        val id = intent.getIntExtra("id", 0)

        findViewById<ImageView>(R.id.back).setOnClickListener {
            onBackPressed()
        }
        val fragment =
            supportFragmentManager.findFragmentByTag(ArticleListFragment::class.java.name) as? ArticleListFragment
                ?: ArticleListFragment.createInstance(id)

        if (!fragment.isAdded) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, ArticleListFragment::class.java.name)
                .commit()
        }
        val editText = findViewById<EditText>(R.id.content)

        findViewById<View>(R.id.search).setOnClickListener {
            fragment.setKeyWord(editText.text.toString())
        }


        editText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                fragment.setKeyWord(editText.text.toString())
            }

            return@setOnEditorActionListener true
        }

    }
}