package com.jun.template.data.repositorysource

import com.jun.template.common.model.Resource
import com.jun.template.data.entity.Temp
import kotlinx.coroutines.flow.Flow

interface TempRepositorySource {

    suspend fun fetchTempData(): Flow<Resource<Temp>>

    suspend fun getTempData(): Flow<Resource<Temp>>
}