package com.ke.wanandroid.mine.model

import com.ke.wanandroid.mine.R

sealed class UserInfo {
    data class LoginUser(
        val coinCount: Int,
        val level: Int,
        val username: String,
        val userId: Int,
        val iconImagePath: String?,
        val backgroundImagePath: String?
    ) : UserInfo() {
        val levelMessage = "等级:$level 排名:${level}"
        val userIdMessage = "ID:$userId"
    }

    object NoLogin : UserInfo() {
    }

    val defaultIconSrc = R.drawable.me_default_avatar
    val defaultBackgroundSrc = R.drawable.me_header
}
