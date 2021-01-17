package com.ke.wanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.mine.ui.mine.MineFragment
import com.ke.wanandroid.officialaccount.ui.officialaccounts.OfficialAccountsFragment
import com.ke.wanandroid.system.ui.system.SystemFragment
import com.ke.wanandroid.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@Route(path = PagePath.MAIN)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var lastFragmentIndex = -1

    private val fragmentList = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lastFragmentIndex = savedInstanceState?.getInt(LAST_FRAGMENT_INDEX, -1) ?: -1

        initFragmentList()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> showFragment(0)
                R.id.action_official_accounts -> showFragment(1)
                R.id.action_system -> showFragment(2)
                R.id.action_me -> showFragment(3)
            }
            return@setOnNavigationItemSelectedListener true
        }
        if (lastFragmentIndex == -1)
            bottomNavigationView.selectedItemId = R.id.action_home
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(LAST_FRAGMENT_INDEX, lastFragmentIndex)
    }

    private fun showFragment(index: Int) {
        if (index < 0 || index >= fragmentList.size) {
            return
        }
        setTranslucentStatus(index == fragmentList.size - 1)

        val transaction = supportFragmentManager.beginTransaction()
        if (lastFragmentIndex >= 0 && lastFragmentIndex < fragmentList.size) {
            transaction.hide(fragmentList[lastFragmentIndex])
        }
        val current = fragmentList[index]
        if (!current.isAdded) {
            //添加Fragment到Activity并设置Tag，方便重启之后找
            transaction.add(R.id.fragment_container, current, current.javaClass.name)
        }
        transaction.show(current)
        transaction.commit()
        lastFragmentIndex = index
    }

    private fun initFragmentList() {
        fragmentList.add(
            supportFragmentManager.findFragmentByTag(HomeFragment::class.java.name)
                ?: HomeFragment()
        )

        fragmentList.add(
            supportFragmentManager.findFragmentByTag(OfficialAccountsFragment::class.java.name)
                ?: OfficialAccountsFragment()
        )

        fragmentList.add(
            supportFragmentManager.findFragmentByTag(SystemFragment::class.java.name)
                ?: SystemFragment()
        )
        fragmentList.add(
            supportFragmentManager.findFragmentByTag(MineFragment::class.java.name)
                ?: MineFragment()
        )
    }

    protected fun setTranslucentStatus(on: Boolean) {
        val win: Window = window
        val winParams = win.attributes
        val bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    companion object {
        private const val LAST_FRAGMENT_INDEX = "LAST_FRAGMENT_INDEX"
    }
}