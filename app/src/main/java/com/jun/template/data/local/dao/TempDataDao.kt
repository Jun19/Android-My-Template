package com.jun.template.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.jun.template.data.entity.Temp

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