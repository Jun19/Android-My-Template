package com.jun.template.data.datasource

import com.jun.template.data.dto.Resource
import com.jun.template.data.vo.Temp

interface TempDataSource {

    suspend fun fetchTempData():Resource<Temp>

    suspend fun getTempData():Resource<Temp>
}