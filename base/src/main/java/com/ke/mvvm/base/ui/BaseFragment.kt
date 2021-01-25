package com.ke.mvvm.base.ui

import android.content.Context
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {
    /**
     * 隐藏软键盘
     */
    fun hideKeyboard() {
        activity?.apply {
            val view = currentFocus
            if (view != null) {
                val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(view.windowToken, 0)
                view.clearFocus()
            }
        }

    }

    /**
     * 沉浸式状态栏
     */
    protected fun setTranslucentStatus(on: Boolean) {
        val win: Window = activity?.window ?: return
        val winParams = win.attributes
        val bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    fun onBackPressed() {
        activity?.onBackPressed()
    }

    protected open fun setupRetryAndLoading(
        retryView: View,
        loadingView: View,
        contentView: View,
        baseViewModel: BaseViewModel
    ) {
        baseViewModel.loadingViewVisible.observe(viewLifecycleOwner) {
            loadingView.isVisible = it
        }
        baseViewModel.retryViewVisible.observe(viewLifecycleOwner) {
            retryView.isVisible = it
        }
        baseViewModel.contentViewVisible.observe(viewLifecycleOwner) {
            contentView.isVisible = it
        }
        retryView.setOnClickListener {
            baseViewModel.retry()
        }
    }

    protected open fun setupRetry(
        retryView: View,
        contentView: View,
        viewModel: BaseViewModel
    ) {
        retryView.setOnClickListener {
            viewModel.retry()
        }
        viewModel.retryViewVisible.observe(viewLifecycleOwner) {
            retryView.isVisible = it
            contentView.isVisible = !it
        }
    }


    protected open fun setupSnackbar(viewModel: BaseViewModel) {
        viewModel.snackbarEvent.observe(viewLifecycleOwner) { action ->
            view?.apply {
                Snackbar.make(this, action.message, action.duration).apply {
                    if (action.action != null && action.actionName != null) {
                        setAction(action.actionName) {
                            action.action.invoke()
                        }
                    }
                }.show()
            }
        }
    }
}