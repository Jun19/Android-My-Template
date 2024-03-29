package com.jun.template.data.remote.api

import com.jun.template.data.entity.Temp
import retrofit2.Retrofit

/**
 * Temp服务
 *
 * @author Jun
 * @time 2022/2/18
 */
class TempService(retrofit: Retrofit) :TempApi{
    private val tempApi: TempApi by lazy { retrofit.create(TempApi::class.java) }

    override suspend fun getTempData(url: String): Temp {
        return tempApi.getTempData(url)
    }
}