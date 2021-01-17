package com.ke.mvvm.base.ui

import android.content.Context
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

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
}