package com.ke.wanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.officialaccount.ui.officialaccounts.OfficialAccountsFragment
import com.ke.wanandroid.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@Route(path = PagePath.MAIN)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, OfficialAccountsFragment())
                .commit()
        }
    }
}