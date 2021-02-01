package com.ke.wanandroid.settings.model

import androidx.appcompat.app.AppCompatDelegate

enum class NightMode(val mode: Int, val showName: String) {
    FOLLOW_SYSTEM(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, "跟随系统"),
    NIGHT(AppCompatDelegate.MODE_NIGHT_YES, "夜间模式"),
    LIGHT(AppCompatDelegate.MODE_NIGHT_NO, "白天模式"),
    AUTO_BATTERY(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY, "跟随电量自动开启或关闭深色模式"),
}

/**
 * 当前夜间模式
 */
var currentNightMode: NightMode
    get() {
        return when (AppCompatDelegate.getDefaultNightMode()) {
            NightMode.NIGHT.mode -> NightMode.NIGHT
            NightMode.LIGHT.mode -> NightMode.LIGHT
            NightMode.AUTO_BATTERY.mode -> NightMode.AUTO_BATTERY
            else -> NightMode.FOLLOW_SYSTEM


        }
    }
    set(value) {
        AppCompatDelegate.setDefaultNightMode(value.mode)
    }