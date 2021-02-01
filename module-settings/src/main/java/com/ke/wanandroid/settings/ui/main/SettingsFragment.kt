package com.ke.wanandroid.settings.ui.main

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseFragment
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.settings.R
import com.ke.wanandroid.settings.databinding.SettingsFragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment(R.layout.settings_fragment_settings) {

    private val binding: SettingsFragmentSettingsBinding by viewbind()
    private val viewModel: SettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSnackbar(viewModel)
        binding.apply {

            viewModel.logoutButtonVisible.observe(viewLifecycleOwner) {
                logout.isVisible = it
            }
            val progressDialog = ProgressDialog(requireContext())
            progressDialog.setTitle("加载中")

            viewModel.loadingDialogShowing.observe(viewLifecycleOwner) {
                if (it) {
                    progressDialog.show()
                } else {
                    progressDialog.dismiss()
                }
            }
            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }

            nightMode.setOnClickListener {
                ARouter.getInstance().build(PagePath.NIGHT_MODE_SETTINGS).navigation()
            }
            logout.setOnClickListener {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("提示")
                    .setMessage("确定退出登录吗？")
                    .setPositiveButton("退出登录") { _, _ ->
                        viewModel.logout()
                    }.setNegativeButton("取消", null)
                    .show()
            }
        }
    }
}