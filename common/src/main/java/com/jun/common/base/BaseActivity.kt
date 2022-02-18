package com.jun.common.base


import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.PermissionUtils
import com.jun.common.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onStart() {
        super.onStart()

    }

    fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun toast(res: Int) {
        Toast.makeText(this, res, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
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

}