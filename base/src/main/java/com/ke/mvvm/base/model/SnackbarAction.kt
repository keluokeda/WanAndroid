package com.ke.mvvm.base.model

import android.view.View
import com.google.android.material.snackbar.Snackbar

data class SnackbarAction(
    val message: String,
    val duration: Int = Snackbar.LENGTH_SHORT,
    val actionName: String? = null,
    val action: (() -> Unit)? = null
) {
    fun show(view: View) {
        Snackbar.make(view, message, duration)
            .apply {
                if (actionName != null && action != null) {
                    setAction(actionName) {
                        action.invoke()
                    }
                }
            }.show()
    }
}