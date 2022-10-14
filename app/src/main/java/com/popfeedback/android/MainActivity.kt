package com.popfeedback.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.popfeedback.ui.PopFeedbackDefaultPopup

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn : Button = findViewById(R.id.button234)

        btn.setOnClickListener {
            PopFeedbackDefaultPopup().show(supportFragmentManager,"feedback")
        }
    }
}