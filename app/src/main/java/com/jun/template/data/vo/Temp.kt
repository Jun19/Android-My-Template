package com.jun.template.data.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * temp
 *
 * @author Jun
 * @time 2022/2/18
 */
@Entity(tableName = "temp_data")
data class Temp(
    @PrimaryKey(autoGenerate = true)
    var id: Int = -1,
    @ColumnInfo(name = "name")
    var name: String
) {
}