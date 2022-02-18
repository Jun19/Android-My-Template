package com.jun.common.base


import android.os.Bundle
import androidx.viewbinding.ViewBinding


abstract class BaseBindingActivity<VB : ViewBinding> : BaseActivity() {

    lateinit var mBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = getViewBinding();
        setContentView(mBinding.root)
        init()
    }

    abstract fun getViewBinding(): VB

    abstract fun init()

}