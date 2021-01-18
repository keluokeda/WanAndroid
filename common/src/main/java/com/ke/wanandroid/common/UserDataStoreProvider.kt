package com.ke.wanandroid.common

import com.ke.wanandroid.common.data.UserDataStore

interface UserDataStoreProvider {

    val store: UserDataStore
}