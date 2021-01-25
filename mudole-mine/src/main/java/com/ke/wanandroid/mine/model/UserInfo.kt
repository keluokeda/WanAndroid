package com.ke.wanandroid.mine.model

import com.ke.wanandroid.mine.R

sealed class UserInfo {
    data class LoginUser(
        val coinCount: Int,
        val level: Int,
        val username: String,
        val userId: Int,
        val rank: Int,
        val iconImagePath: String?,
        val backgroundImagePath: String?
    ) : UserInfo() {
        val levelMessage = "等级:$level 排名:${rank}"
        val userIdMessage = "ID:$userId"
    }

    object NoLogin : UserInfo()

    val defaultIconSrc = R.drawable.mine_default_avatar
    val defaultBackgroundSrc = R.drawable.mine_header
}
