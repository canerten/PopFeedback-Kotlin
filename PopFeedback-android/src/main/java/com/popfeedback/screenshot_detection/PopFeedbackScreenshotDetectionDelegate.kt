package com.popfeedback.screenshot_detection

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.database.ContentObserver
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Handler
import android.provider.MediaStore
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.coroutines.*
import java.lang.ref.WeakReference
import java.util.*
import kotlin.concurrent.schedule

class PopFeedbackScreenshotDetectionDelegate(
    activityWeakReference: Activity,
    listener: ScreenshotDetectionListener?
) {
    private val activityWeakReference: WeakReference<Activity>
    private val listener: ScreenshotDetectionListener?

    var hasRan = false
    fun startScreenshotDetection() {
        activityWeakReference.get()
            ?.getContentResolver()
            ?.registerContentObserver(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                true,
                contentObserver
            )
    }

    fun stopScreenshotDetection() {
        activityWeakReference.get()!!.contentResolver.unregisterContentObserver(contentObserver)
    }

    private val contentObserver: ContentObserver = object : ContentObserver(Handler()) {
        override fun deliverSelfNotifications(): Boolean {
            return super.deliverSelfNotifications()
        }

        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)
        }

        override fun onChange(selfChange: Boolean, uri: Uri?) {
            super.onChange(selfChange, uri)
            if (isReadExternalStoragePermissionGranted) {
                val path = getFilePathFromContentResolver(activityWeakReference, uri)
                if (isScreenshotPath(path)) {
                    Timer("SettingUp", false).schedule(1000) {
                        hasRan = false
                    }
                    if(!hasRan){
                        onScreenCaptured(path)
                        hasRan = true
                    }
                }
            } else {
                onScreenCapturedWithDeniedPermission()
            }
        }
    }

    private fun onScreenCaptured(path: String?) {
        listener?.onScreenCaptured(path)
    }

    private fun onScreenCapturedWithDeniedPermission() {
        listener?.onScreenCapturedWithDeniedPermission()
    }

    private fun isScreenshotPath(path: String?): Boolean {
        return path != null && path.toLowerCase().contains("screenshots")
    }

    @SuppressLint("Range")
    private fun getFilePathFromContentResolver(context: Context, uri: Uri?): String? {
        try {
            val cursor: Cursor? = context.contentResolver.query(
                uri!!, arrayOf(
                    MediaStore.Images.Media.DISPLAY_NAME,
                    MediaStore.Images.Media.DATA
                ), null, null, null
            )
            if (cursor != null && cursor.moveToFirst()) {
                val path: String =
                    cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
                cursor.close()
                return path
            }
        } catch (ignored: IllegalStateException) {
        }
        return null
    }

    private val isReadExternalStoragePermissionGranted: Boolean
        private get() = ContextCompat.checkSelfPermission(
            activityWeakReference.get()!!,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

    interface ScreenshotDetectionListener {
        fun onScreenCaptured(path: String?)
        fun onScreenCapturedWithDeniedPermission()
    }

    init {
        this.activityWeakReference = WeakReference(activityWeakReference)
        this.listener = listener
    }
}