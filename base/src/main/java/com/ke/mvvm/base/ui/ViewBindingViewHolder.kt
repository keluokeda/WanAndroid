package com.ke.mvvm.base.ui

import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class ViewBindingViewHolder<VB : ViewBinding>( val viewBinding: VB) :
    BaseViewHolder(viewBinding.root)