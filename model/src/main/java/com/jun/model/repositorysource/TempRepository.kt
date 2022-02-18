package com.jun.model.repositorysource

import com.jun.model.dto.Resource
import com.jun.model.local.TempLocalData
import com.jun.model.remote.TempRemoteData
import com.jun.model.vo.Temp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

/**
 * temp数据储存库
 *
 * @author Jun
 * @time 2022/2/18
 */
class TempRepository constructor(
    private val tempLocalData: TempLocalData,
    private val tempRemoteData: TempRemoteData,
    private val ioDispatcher: CoroutineContext
) : TempRepositorySource  {
    override suspend fun fetchTempData(): Flow<Resource<Temp>> {
        return flow {
            emit(tempRemoteData.fetchTempData())
        }.flowOn(ioDispatcher)
    }

    override suspend fun getTempData(): Flow<Resource<Temp>> {
        return flow {
            emit(tempLocalData.getTempData())
        }.flowOn(ioDispatcher)
    }
}