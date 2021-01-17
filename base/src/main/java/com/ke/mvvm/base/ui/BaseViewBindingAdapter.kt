package com.ke.mvvm.base.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import java.util.*

abstract class BaseViewBindingAdapter<T, VB : ViewBinding> :
    BaseQuickAdapter<T, ViewBindingViewHolder<VB>>(0) {


    abstract fun createViewBinding(inflater: LayoutInflater, parent: ViewGroup): VB

    override fun onCreateDefViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingViewHolder<VB> {
        val viewBinding = createViewBinding(LayoutInflater.from(parent.context), parent)
        return ViewBindingViewHolder(viewBinding)
    }


}