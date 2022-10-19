package com.popfeedback.android

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.popfeedback.ui.PopFeedbackDefaultPopup


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn : Button = findViewById(R.id.button234)

        btn.setOnClickListener {
            PopFeedbackDefaultPopup().show(supportFragmentManager,"feedback")
        }

        detectScreenShotService(this)
    }

    fun detectScreenShotService(activity: Activity) {
        val h = Handler()
        val delay = 3000 //milliseconds
        val am = activity.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        h.postDelayed(object : Runnable {
            override fun run() {
                val rs = am.getRunningServices(200)
                for (ar in rs) {
                    if (ar.process == "com.android.systemui:screenshot") {
                        Toast.makeText(activity, "Screenshot captured!!", Toast.LENGTH_LONG).show()
                    }
                }
                h.postDelayed(this, delay.toLong())
            }
        }, delay.toLong())
    }
}