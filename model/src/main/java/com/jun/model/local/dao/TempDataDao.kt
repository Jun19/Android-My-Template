package com.jun.model.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.jun.model.vo.Temp

/**
 * Temp interface
 *
 * @author Jun
 * @time 2022/2/18
 */
@Dao
interface TempDataDao : BaseDao<Temp> {
    @Query("select * from temp_data limit 1")
    fun queryAll(): Temp
}