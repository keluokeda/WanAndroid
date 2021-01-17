package com.ke.mvvm.base.ui

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.ke.mvvm.base.R

abstract class BaseFragmentActivity : BaseActivity() {

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_fragment_container)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment)
                .commit()
        }

    }

    abstract val fragment: Fragment
}