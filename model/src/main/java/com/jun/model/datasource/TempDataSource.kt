package com.jun.model.datasource

import com.jun.model.dto.Resource
import com.jun.model.vo.Temp

interface TempDataSource {

    suspend fun fetchTempData():Resource<Temp>

    suspend fun getTempData():Resource<Temp>
}