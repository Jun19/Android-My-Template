package com.jun.common.utils

import android.util.Log
import com.jun.common.BuildConfig.DEBUG
import com.jun.common.Constants

/**
 * logger管理
 *
 * @author Jun
 * @time 2021/11/23
 */
object L {

    fun d(msg: String) {
        if (DEBUG) {
            Log.d(Constants.LOGGER_TAG_NAME, msg)
        }
    }

    fun d(tag: String, msg: String) {
        if (DEBUG) {
            Log.d(tag, "msg: ")
        }
    }

    fun e(tagName: String, msg: String) {
        if (DEBUG) {
            Log.e(tagName, msg)
        }
    }

    fun e(msg: String) {
        if (DEBUG) {
            Log.e(Constants.LOGGER_TAG_NAME, msg)
        }
    }

}