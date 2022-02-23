package com.jun.common.base


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    lateinit var vb: VB
    lateinit var mContext: Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = getBinding(inflater, container)
        return vb.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view, savedInstanceState)
    }


    fun runOnUiThread(action: Runnable) {
        activity?.runOnUiThread { action.run() }
    }

    fun toast(msg: String) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show()
    }

    fun toast(res: Int) {
        Toast.makeText(mContext, res, Toast.LENGTH_LONG).show()
    }

    abstract fun init(view: View, savedInstanceState: Bundle?)
    protected abstract fun getBinding(inflater: LayoutInflater, viewGroup: ViewGroup?): VB


}