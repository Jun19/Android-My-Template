package com.jun.common.utils

import android.content.IntentSender.SendIntentException
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.tasks.Task
import com.jun.common.R
import kotlin.properties.Delegates

object UpdateUtil : LifecycleObserver {
    private var mAppUpdateManager: AppUpdateManager? = null
    private var mInstallStateUpdatedListener: InstallStateUpdatedListener? = null
    private var mSnackbar: Snackbar? = null
    private const val REQ_CODE_VERSION_UPDATE = 530
    private var mAvailableVersionCode by Delegates.notNull<Int>()

    private var mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                0 -> mSnackbar?.dismiss()
            }
        }
    }

    fun startFlexibleUpdate(activity: AppCompatActivity) {
        startUpdateForType(activity, updateType = AppUpdateType.FLEXIBLE)
        mInstallStateUpdatedListener = InstallStateUpdatedListener { state: InstallState ->
            if (state.installStatus() == InstallStatus.DOWNLOADED) {
                popupSnackbarForCompleteUpdate()
            }
        }
        mAppUpdateManager?.registerListener(mInstallStateUpdatedListener!!)
    }

    fun startImmediateUpdate(activity: AppCompatActivity) {
        startUpdateForType(activity, updateType = AppUpdateType.IMMEDIATE)
    }

    private fun startUpdateForType(activity: AppCompatActivity, @AppUpdateType updateType: Int) {
        activity.lifecycle.addObserver(this)
        mAppUpdateManager = AppUpdateManagerFactory.create(activity)
        val appUpdateInfoTask: Task<AppUpdateInfo> = mAppUpdateManager!!.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener(com.google.android.play.core.tasks.OnSuccessListener { appUpdateInfo: AppUpdateInfo ->
            if (updateType == AppUpdateType.FLEXIBLE && appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                popupSnackbarForCompleteUpdate()
                return@OnSuccessListener
            }
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(updateType)
            ) {
                mAvailableVersionCode = appUpdateInfo.availableVersionCode()
                try {
                    mAppUpdateManager?.startUpdateFlowForResult(
                        appUpdateInfo,
                        updateType,
                        activity,
                        REQ_CODE_VERSION_UPDATE
                    )
                } catch (e: SendIntentException) {
                    e.printStackTrace()
                }
            }
        })
    }

    private fun popupSnackbarForCompleteUpdate() {
        val currentActivity = ActivityLifecycleTracker.getCurrentActivity()
        currentActivity?.let {
            val rootView: View =
                currentActivity.window.decorView.findViewById(android.R.id.content)
            mSnackbar = Snackbar.make(
                rootView,
                R.string.update_finish_tips,
                Snackbar.LENGTH_INDEFINITE
            )
            mSnackbar?.setAction(R.string.update_finish_restart) {
                mAppUpdateManager?.completeUpdate()
            }
            mSnackbar?.setActionTextColor(
                ContextCompat.getColor(it, R.color.snackbar_action_text_color)
            )
            mSnackbar?.show()
            mHandler.sendEmptyMessageDelayed(0, 6000)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        mInstallStateUpdatedListener?.let { mAppUpdateManager?.unregisterListener(it) }
    }
}