package com.ke.wanandroid.data

import com.ke.wanandroid.api.WanApiService
import com.ke.wanandroid.api.response.WanBaseListResponse
import com.ke.wanandroid.api.response.WanNavigationResponse
import javax.inject.Inject
import javax.inject.Singleton

interface NavigationRepository {

    suspend fun getNavigationTree(): WanBaseListResponse<WanNavigationResponse>
}

@Singleton
class NavigationRepositoryImpl @Inject constructor(private val wanApiService: WanApiService) :
    NavigationRepository {
    override suspend fun getNavigationTree(): WanBaseListResponse<WanNavigationResponse> {
        return wanApiService.getNavigationTree()
    }

}