package com.jun.common.base


import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.PermissionUtils


abstract class BaseFragment : Fragment() {

    lateinit var mContext: Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    fun toast(msg: String) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show()
    }

    fun toast(res: Int) {
        Toast.makeText(mContext, res, Toast.LENGTH_LONG).show()
    }

    fun requestPermission(constants: String, callback: () -> Unit) {
        PermissionUtils.permission(constants)
            .callback(object : PermissionUtils.SimpleCallback {
                override fun onGranted() {
                    callback.invoke()
                }

                override fun onDenied() {}
            }).request()
    }

    fun runOnUiThread(action: Runnable) {
        activity?.runOnUiThread { action.run() }
    }
}