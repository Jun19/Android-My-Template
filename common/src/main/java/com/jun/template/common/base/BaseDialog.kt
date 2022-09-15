package com.jun.template.common.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.jun.template.common.R

/**
 *
 *
 * @author Jun
 * @time 2022/6/29
 */
abstract class BaseDialog<VB : ViewBinding>(context: Context) :
    Dialog(context, R.style.RoundDialogTheme) {

    lateinit var vb: VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = getViewBinding()
        setContentView(vb.root)
        init()
    }


    abstract fun getViewBinding(): VB
    abstract fun init()
}