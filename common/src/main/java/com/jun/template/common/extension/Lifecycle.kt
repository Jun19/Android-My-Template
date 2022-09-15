package com.jun.template.common.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.jun.template.common.exception.DataException

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <L : LiveData<DataException>> LifecycleOwner.failure(
    liveData: L,
    body: (DataException?) -> Unit
) = liveData.observe(this, Observer(body))