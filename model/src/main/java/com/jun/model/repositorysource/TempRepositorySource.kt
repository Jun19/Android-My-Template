package com.jun.model.repositorysource

import com.jun.model.dto.Resource
import com.jun.model.vo.Temp
import kotlinx.coroutines.flow.Flow

interface TempRepositorySource {

    suspend fun fetchTempData(): Flow<Resource<Temp>>

    suspend fun getTempData(): Flow<Resource<Temp>>
}