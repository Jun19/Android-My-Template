package com.jun.common.base


import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.BaseQuickAdapter


abstract class BaseBindingAdapter<T, VB : ViewBinding>(data: MutableList<T>? = null) :
    BaseQuickAdapter<T, VBViewHolder<VB>>(0, data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VBViewHolder<VB> {
        val createVB = createVB(parent, viewType)
        return VBViewHolder(createVB, createVB.root)
    }

    abstract fun createVB(parent: ViewGroup, viewType: Int): VB

}