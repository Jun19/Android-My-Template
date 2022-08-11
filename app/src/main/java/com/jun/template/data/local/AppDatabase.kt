package com.jun.template.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jun.common.Constants
import com.jun.template.data.local.dao.TempDataDao
import com.jun.template.data.vo.Temp

/**
 * room database
 *
 * @author Jun
 * @time 2022/2/18
 */
@Database(entities = [Temp::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getTempDataDao(): TempDataDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDBInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            Constants.DATABASE_NAME
                        ).allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}