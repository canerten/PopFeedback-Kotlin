package com.popfeedback.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.popfeedback.popfeedback_android.R
import com.popfeedback.visual_options.PFFeedbackPopVisualOption


class PopFeedbackDefaultPopup() : DialogFragment() {

    lateinit var rootView : View

    lateinit var titleText : TextView
    lateinit var bugReportButton : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
        //setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Translucent_NoTitleBar);
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.popfeedback_default_popup, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog?.setCanceledOnTouchOutside(true)

        initViews()
        setupUI()
        onClickListeners()

        return rootView
    }

    override fun onResume() {
        super.onResume()

        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    fun setupUI(){

    }

    fun initViews(){
        titleText = rootView.findViewById(R.id.popFeedbackDefaultPopupTitleText)
        bugReportButton = rootView.findViewById(R.id.bugReportButton)
    }

    fun onClickListeners(){
        bugReportButton.setOnClickListener {
            bugReportTapped()
        }
    }

    fun bugReportTapped(){
        PopFeedbackReportAndFeedbackPopup().show(parentFragmentManager, "")
        dismiss();
    }
}