package com.ke.wanandroid.settings.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.alibaba.android.arouter.launcher.ARouter
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseFragment
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.settings.R
import com.ke.wanandroid.settings.databinding.SettingsFragmentSettingsBinding


class SettingsFragment : BaseFragment(R.layout.settings_fragment_settings) {

    private val binding: SettingsFragmentSettingsBinding by viewbind()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            currentNightMode.text = AppCompatDelegate.getDefaultNightMode().toString()
            nightMode.setOnClickListener {
                ARouter.getInstance().build(PagePath.NIGHT_MODE_SETTINGS).navigation()
            }
        }
    }
}