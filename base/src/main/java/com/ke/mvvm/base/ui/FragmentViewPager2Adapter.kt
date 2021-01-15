package com.ke.mvvm.base.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentViewPager2Adapter<T>(
    fragment: Fragment,
    private val list: List<T>,
    private val creator: (T) -> Fragment
) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return creator(list[position])
    }
}