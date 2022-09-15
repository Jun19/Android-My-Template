package com.jun.template.data.datasource

import com.jun.template.common.model.Resource
import com.jun.template.data.entity.Temp

interface TempDataSource {

    suspend fun fetchTempData():Resource<Temp>

    suspend fun getTempData():Resource<Temp>
}