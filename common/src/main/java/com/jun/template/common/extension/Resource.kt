package com.jun.template.common.extension

import androidx.annotation.NonNull
import com.jun.template.common.exception.DataException
import com.jun.template.common.model.Resource

fun <T : Any> toResourceLocal(@NonNull block: () -> T): Resource<T> {
    return try {
        Resource.Success(block.invoke())
    } catch (e: Exception) {
        e.printStackTrace()
        Resource.Failure(DataException.LocalError(e.toString()))
    }
}

fun <T : Any> toResourceCustomException(
    @NonNull block: () -> T,
    dataException: DataException
): Resource<T> {
    return try {
        Resource.Success(block.invoke())
    } catch (e: Exception) {
        e.printStackTrace()
        Resource.Failure(dataException)
    }
}