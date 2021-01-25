package com.ke.wanandroid.settings.nightmode

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseFragment
import com.ke.wanandroid.common.nightModeList
import com.ke.wanandroid.settings.R
import com.ke.wanandroid.settings.databinding.SettingsFragmentNightModeBinding


class NightModeFragment : BaseFragment(R.layout.settings_fragment_night_mode) {


    private val binding: SettingsFragmentNightModeBinding by viewbind()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val selectedIndex = nightModeList.indexOf(AppCompatDelegate.getDefaultNightMode())
            val radioList =
                listOf(modeDefault, modeNight, modeDay, modeAutoBattery, modeFollowSystem)

            radioList[selectedIndex].isChecked = true

            radioList.forEachIndexed { index, materialRadioButton ->
                materialRadioButton.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        AppCompatDelegate.setDefaultNightMode(nightModeList[index])
                    }
                }
            }

            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }
}