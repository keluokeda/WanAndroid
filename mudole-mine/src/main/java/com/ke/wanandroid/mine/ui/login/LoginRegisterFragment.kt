package com.ke.wanandroid.mine.ui.login

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseFragment
import com.ke.wanandroid.mine.R
import com.ke.wanandroid.mine.databinding.MineFragmentLoginRegisterBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginRegisterFragment : BaseFragment(R.layout.mine_fragment_login_register) {

    private val binding: MineFragmentLoginRegisterBinding by viewbind()
    private val viewModel: LoginRegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.login.setOnClickListener {
            val username = binding.account.text.toString().trim()
            val password = binding.password.text.toString().trim()
            viewModel.login(username, password, true)
        }
        binding.register.setOnClickListener {
            val username = binding.account.text.toString().trim()
            val password = binding.password.text.toString().trim()
            viewModel.login(username, password, false)
        }
        viewModel.loadingViewVisible.observe(viewLifecycleOwner) {
            binding.loadingView.isVisible = it
            binding.login.isEnabled = !it
            binding.register.isEnabled = !it
            binding.account.isEnabled = !it
            binding.password.isEnabled = !it
        }

        viewModel.snackbarEvent.observe(viewLifecycleOwner) {
            it.show(binding.root)
        }

        viewModel.loginResult.observe(viewLifecycleOwner) {
            if (it) {
                onBackPressed()
            }
        }
    }
}