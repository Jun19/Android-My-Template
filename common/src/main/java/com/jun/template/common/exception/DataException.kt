package com.jun.template.common.exception

sealed class DataException(
    val errorCode: Int = 0,
    val errorMsg: String = ""
) {

    object NetworkUnavailable : DataException()
    class LocalError(errorMsg: String) : DataException(errorMsg = errorMsg)
    class ServerError(errorCode: Int, errorMsg: String) : DataException(errorCode, errorMsg)

    override fun toString(): String {
        return "${this.javaClass.name}: errorCode: $errorCode, errorMsg: $errorMsg"
    }
}
