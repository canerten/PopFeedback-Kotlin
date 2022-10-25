package com.popfeedback.android

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.popfeedback.models.enums.PFBuilderType
import com.popfeedback.popfeedback_android.PopFeedback
import com.popfeedback.screenshot_detection.PopFeedbackActivity


class MainActivity : PopFeedbackActivity(builderType = PFBuilderType.REGULAR) {

    lateinit var img : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var x : Button = findViewById(R.id.button234)
        img  = findViewById(R.id.imageView123)

        x.setOnClickListener {
            screenShot(window.decorView.rootView)
        }

        PopFeedback
    }

    override fun onScreenCaptured(path: String?) {
        super.onScreenCaptured(path)

        // IF NOT REGULAR THEN BUILD MANUALLY

//        val bitmapImg = takeScreenShot(window.decorView.rootView)
//        val ssDialog = bitmapImg?.let {
//            PFScreenShotDialogBuilder.Builder()
//                .setScreenshotImage(
//                    bitmapImg = it
//                )
//                .build()
//        }
//        ssDialog?.setDialogButtonClickListener(object : PFScreenShotDialogBuilder.DialogButtonClickListener{
//            override fun onBugReportClick() {
//                ssDialog.dismiss()
//                val bugAndFeedbackDialog = PFBugAndFeedbackDialogBuilder.Builder()
//                    .build()
//                bugAndFeedbackDialog.show(supportFragmentManager,"bug_and_feedback_dialog")
//            }
//
//            override fun onFeedbackClick() {
//                ssDialog.dismiss()
//                val customDialog = CustomDialog.Builder()
//                    .setTitle(applicationContext.getString(com.popfeedback.popfeedback_android.R.string.pop_feedback_default_title_text))
//                    .setBugReportButtonText(applicationContext.getString(com.popfeedback.popfeedback_android.R.string.bug_report_text))
//                    .setFeedbackButtonText(applicationContext.getString(com.popfeedback.popfeedback_android.R.string.feedback_text))
//                    .setBackgroundColor(ContextCompat.getColor(applicationContext,
//                        com.popfeedback.popfeedback_android.R.color.hint_color))
//                    .build()
//                customDialog.show(supportFragmentManager,"custom_dialog")
//            }
//
//            override fun onShareClick() {
//                //Share Tapped
//            }
//
//        })
//        ssDialog?.show(supportFragmentManager,"ss_dialog")
    }
}