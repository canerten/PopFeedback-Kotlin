package com.popfeedback.screenshot_detection

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.popfeedback.builders.PFDefaultPopupBuilder
import com.popfeedback.builders.PFBugAndFeedbackDialogBuilder
import com.popfeedback.builders.PFScreenShotDialogBuilder
import com.popfeedback.models.enums.PFBuilderType
import java.util.*
import kotlin.concurrent.schedule


abstract class PopFeedbackActivity(var builderType : PFBuilderType) : AppCompatActivity(),
    PopFeedbackScreenshotDetectionDelegate.ScreenshotDetectionListener {
    private val screenshotDetectionDelegate = PopFeedbackScreenshotDetectionDelegate(this, this)
    var hasRan = false
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkReadExternalStoragePermission()
    }

    override fun onStart() {
        super.onStart()
        screenshotDetectionDelegate.startScreenshotDetection()
    }

    override fun onStop() {
        super.onStop()
        screenshotDetectionDelegate.stopScreenshotDetection()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_READ_EXTERNAL_STORAGE_PERMISSION -> if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                showReadExternalStoragePermissionDeniedMessage()
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onScreenCaptured(path: String?) {
        // Do something when screen was captured

        if(builderType == PFBuilderType.REGULAR){
            Timer("SettingUp", false).schedule(1000) {
                hasRan = false
            }
            if(!hasRan){
//            PopFeedbackDefaultPopup().show(supportFragmentManager,"feedback")
//            val customDialog = CustomDialog.Builder()
//                .setTitle(applicationContext.getString(R.string.pop_feedback_default_title_text))
//                .setBugReportButtonText(applicationContext.getString(R.string.bug_report_text))
//                .setFeedbackButtonText(applicationContext.getString(R.string.feedback_text))
//                .setBackgroundColor(ContextCompat.getColor(applicationContext,R.color.hint_color))
//                .build()
//            customDialog.show(supportFragmentManager,"custom_dialog")

                screenShot(window.decorView.rootView)

                hasRan = true

            }
        }
    }

    override fun onScreenCapturedWithDeniedPermission() {
        // Do something when screen was captured but read external storage permission has denied
    }

    open fun buildScreenShotDialog(bitmap : Bitmap){
        val ssDialog = PFScreenShotDialogBuilder.Builder()
            .setScreenshotImage(
                bitmapImg = bitmap
            )
            .build()
        ssDialog.setDialogButtonClickListener(object : PFScreenShotDialogBuilder.DialogButtonClickListener{
            override fun onBugReportClick() {
                ssDialog.dismiss()
                val bugAndFeedbackDialog = PFBugAndFeedbackDialogBuilder.Builder()
                    .build()
                bugAndFeedbackDialog.show(supportFragmentManager,"bug_and_feedback_dialog")
            }

            override fun onFeedbackClick() {
                ssDialog.dismiss()
                val customDialog = PFDefaultPopupBuilder.Builder()
                    .build()
                customDialog.show(supportFragmentManager,"custom_dialog")
            }

            override fun onShareClick() {
                //Share Tapped
            }

        })
        ssDialog.show(supportFragmentManager,"ss_dialog")
    }

    open fun screenShot(view: View): Bitmap? {
        val bitmap = Bitmap.createBitmap(
            view.width,
            view.height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        //img.setImageBitmap(bitmap)

        buildScreenShotDialog(bitmap = bitmap)

        return bitmap
    }

    open fun takeScreenShot(view: View): Bitmap? {
        val bitmap = Bitmap.createBitmap(
            view.width,
            view.height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.draw(canvas)

        return bitmap
    }



    private fun checkReadExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestReadExternalStoragePermission()
        }
    }

    private fun requestReadExternalStoragePermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_CODE_READ_EXTERNAL_STORAGE_PERMISSION
        )
    }

    private fun showReadExternalStoragePermissionDeniedMessage() {
        Toast.makeText(this, "Read external storage permission has denied", Toast.LENGTH_SHORT)
            .show()
    }

    companion object {
        private const val REQUEST_CODE_READ_EXTERNAL_STORAGE_PERMISSION = 3009
    }
}