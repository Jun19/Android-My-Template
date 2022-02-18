package com.jun.model.remote.api

import com.jun.model.vo.Temp
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * retrofit api
 *
 * @author Jun
 * @time 2022/2/18
 */
interface TempApi {
    @GET("tiktok/id")
    suspend fun getTempData(@Query("url") url: String): Temp
}