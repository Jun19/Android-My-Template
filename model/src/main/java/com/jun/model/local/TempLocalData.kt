package com.jun.model.local

import com.jun.common.exception.DataException
import com.jun.model.datasource.TempDataSource
import com.jun.model.dto.Resource
import com.jun.model.local.dao.TempDataDao
import com.jun.model.vo.Temp

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