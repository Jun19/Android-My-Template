package com.jun.template.data.remote

import com.jun.common.exception.DataException
import com.jun.common.net.NetworkHandler
import com.jun.template.data.datasource.TempDataSource
import com.jun.template.data.dto.Resource
import com.jun.template.data.remote.api.TempService
import com.jun.template.data.vo.Temp

/**
 * Temp远程服务
 *
 * @author Jun
 * @time 2022/2/18
 */
class TempRemoteData constructor(
    private val tempService: TempService,
    private val networkHandler: NetworkHandler
) : TempDataSource {
    override suspend fun fetchTempData(): Resource<Temp> {
        if (!networkHandler.isNetworkAvailable()) {
            return Resource.Failure(DataException.NetworkUnavailable)
        }
        return try {
            val response = tempService.getTempData("")
            if (response.id != -1) {
                Resource.Success(response)
            } else {
                Resource.Failure(DataException.ServerError(-1, "data is null."))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(DataException.LocalError(e.toString()))
        }
    }

    override suspend fun getTempData(): Resource<Temp> {
        TODO("Not yet implemented")
    }
}