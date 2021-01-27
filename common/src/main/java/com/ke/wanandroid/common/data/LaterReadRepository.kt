package com.ke.wanandroid.common.data


import androidx.lifecycle.LiveData
import com.ke.wanandroid.api.response.WanArticleResponse
import com.ke.wanandroid.common.db.LaterRead
import com.ke.wanandroid.common.db.LaterReadDao
import com.ke.wanandroid.common.db.toLaterRead
import javax.inject.Inject
import javax.inject.Singleton

interface LaterReadRepository {
    val getAll: LiveData<List<LaterRead>>
    suspend fun insert(wanArticleResponse: WanArticleResponse)
    suspend fun deleteAll()
    suspend fun delete(laterRead: LaterRead)
}

@Singleton
class LaterReadRepositoryImpl @Inject constructor(private val laterReadDao: LaterReadDao) :
    LaterReadRepository {
    override val getAll: LiveData<List<LaterRead>>
        get() = laterReadDao.getAll()

    override suspend fun insert(wanArticleResponse: WanArticleResponse) {
        laterReadDao.insert(wanArticleResponse.toLaterRead())
    }

    override suspend fun deleteAll() {
        laterReadDao.deleteAll()
    }

    override suspend fun delete(laterRead: LaterRead) {
        laterReadDao.delete(laterRead)
    }


}