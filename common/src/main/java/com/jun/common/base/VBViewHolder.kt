package com.jun.common.base


import android.view.View
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.viewholder.BaseViewHolder


class VBViewHolder<VB : ViewBinding>(val vb: VB, view: View) : BaseViewHolder(view)