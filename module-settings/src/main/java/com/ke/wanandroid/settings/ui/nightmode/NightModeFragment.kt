package com.ke.wanandroid.settings.ui.nightmode

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseFragment
import com.ke.wanandroid.settings.R
import com.ke.wanandroid.settings.databinding.SettingsFragmentNightModeBinding
import com.ke.wanandroid.settings.model.NightMode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NightModeFragment : BaseFragment(R.layout.settings_fragment_night_mode) {


    private val binding: SettingsFragmentNightModeBinding by viewbind()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: NightModeViewModel by viewModels()

        binding.apply {
            val modeList = NightMode.values()

            val radioList =
                listOf(modeFollowSystem, modeNight, modeDay, modeAutoBattery)

            viewModel.nightMode.observe(viewLifecycleOwner) {
                val selectedIndex = modeList.indexOf(it)
                radioList[selectedIndex].isChecked = true
            }

            radioList.forEachIndexed { index, materialRadioButton ->
                val mode = modeList[index]
                materialRadioButton.text = mode.showName
                materialRadioButton.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        viewModel.setNightMode(mode)
                    }
                }
            }

            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }
}