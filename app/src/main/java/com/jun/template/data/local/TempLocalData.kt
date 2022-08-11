package com.jun.template.data.local

import com.jun.common.exception.DataException
import com.jun.template.data.datasource.TempDataSource
import com.jun.template.data.dto.Resource
import com.jun.template.data.local.dao.TempDataDao
import com.jun.template.data.vo.Temp

/**
 * Temp本地数据
 *
 * @author Jun
 * @time 2022/2/18
 */
class TempLocalData(private val tempDataDao: TempDataDao) : TempDataSource {
    override suspend fun fetchTempData(): Resource<Temp> {
        TODO("Not yet implemented")
    }

    override suspend fun getTempData(): Resource<Temp> {
        return try {
            Resource.Success(tempDataDao.queryAll())
        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.Failure(DataException.LocalError(e.toString()))
        }
    }
}