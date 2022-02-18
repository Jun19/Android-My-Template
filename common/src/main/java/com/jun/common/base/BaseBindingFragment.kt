package com.jun.common.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding


abstract class BaseBindingFragment<VB : ViewBinding> : BaseFragment() {

    lateinit var mBinding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = getBinding(inflater, container)
        return mBinding.root
    }

    protected abstract fun getBinding(inflater: LayoutInflater, viewGroup: ViewGroup?): VB


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view, savedInstanceState)
    }

    abstract fun init(view: View, savedInstanceState: Bundle?)

}