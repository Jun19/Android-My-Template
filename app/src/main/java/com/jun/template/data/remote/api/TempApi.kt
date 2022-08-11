package com.jun.template.data.remote.api

import com.jun.template.data.vo.Temp
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