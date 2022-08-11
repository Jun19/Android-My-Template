package com.jun.template.data.repositorysource

import com.jun.template.data.dto.Resource
import com.jun.template.data.local.TempLocalData
import com.jun.template.data.remote.TempRemoteData
import com.jun.template.data.vo.Temp
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