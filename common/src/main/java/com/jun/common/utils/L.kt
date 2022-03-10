package com.jun.common.utils

import com.jun.common.BuildConfig.DEBUG
import com.jun.common.Constants
import com.orhanobut.logger.Logger

/**
 * logger管理
 *
 * @author Jun
 * @time 2021/11/23
 */
object L {

    fun d(msg: String) {
        if (DEBUG) {
            Logger.d(Constants.LOGGER_TAG_NAME, msg)
        }
    }

    fun d(tag: String, msg: String) {
        if (DEBUG) {
            Logger.d(tag, "msg: ")
        }
    }

    fun e(tagName: String, msg: String) {
        if (DEBUG) {
            Logger.e(tagName, msg)
        }
    }

    fun e(msg: String) {
        if (DEBUG) {
            Logger.e(Constants.LOGGER_TAG_NAME, msg)
        }
    }

}