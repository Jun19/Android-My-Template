package com.jun.common.base


import android.os.Bundle
import androidx.viewbinding.ViewBinding


abstract class BaseVBActivity<VB : ViewBinding> : BaseActivity() {

    lateinit var vb: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = getViewBinding();
        setContentView(vb.root)
        init()
    }

    abstract fun getViewBinding(): VB
    abstract fun init()


}