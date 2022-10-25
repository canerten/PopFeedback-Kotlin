package com.popfeedback.builders

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment
import com.popfeedback.popfeedback_android.R

class PFDefaultPopupBuilder private constructor(
    private val backgroundColor : Int?,
    private val titleText: String?,
    private val bugReportButtonText: String?,
    private val feedbackButtonText: String?,
    private val titleColor: Int?,
    private val bugReportButtonTextColor: Int?,
    private val feedbackButtonTextColor: Int?,


    private var dialogClickListener: DialogButtonClickListener? = null
) : DialogFragment() {

    lateinit var rootView : View
    lateinit var title : TextView
    lateinit var bugReportButton: Button
    lateinit var feedbackButton: Button
    lateinit var backgroundView : CardView

    interface DialogButtonClickListener{
        fun onBugReportClick()
        fun onFeedbackClick()
    }

    data class Builder(
        private var titleText: String? = "Is there anything you want to share with us ?",
        private var bugReportButtonText: String? = "Bug Report",
        private var feedbackButtonText: String? = "Feedback",
        private var backgroundColor: Int? = null,
        private var titleColor: Int? = Color.BLACK,
        private var bugReportButtonTextColor: Int? = Color.BLACK,
        private var feedbackButtonTextColor: Int? = Color.BLACK,
    ){

        fun setTitle(titleText:String) = apply { this.titleText = titleText }
        fun setBugReportButtonText(bugReportButtonText : String) = apply { this.bugReportButtonText = bugReportButtonText }
        fun setFeedbackButtonText(feedbackButtonText: String) = apply { this.feedbackButtonText = feedbackButtonText }
        fun setBackgroundColor(bgColor : Int) = apply { this.backgroundColor = bgColor }
        fun setTitleColor(titleColor : Int) = apply { this.backgroundColor = titleColor }
        fun setBugReportButtonColor(buttonTextColor : Int) = apply { this.backgroundColor = buttonTextColor }
        fun setFeedbackButtonColor(buttonTextColor : Int) = apply { this.backgroundColor = buttonTextColor }

        fun build() = PFDefaultPopupBuilder(
            backgroundColor = backgroundColor,
            titleText = titleText,
            titleColor = titleColor,
            bugReportButtonText = bugReportButtonText,
            bugReportButtonTextColor = bugReportButtonTextColor,
            feedbackButtonText = feedbackButtonText,
            feedbackButtonTextColor = feedbackButtonTextColor,
        )

    }

    fun setDialogButtonClickListener(listener:DialogButtonClickListener) = apply { dialogClickListener = listener }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView =  inflater.inflate(R.layout.popfeedback_default_popup,container)

        setViews()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog?.setCanceledOnTouchOutside(true)

        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title.text = titleText
        bugReportButton.text = bugReportButtonText
        feedbackButton.text = feedbackButtonText
        if (backgroundColor != null) {
            backgroundView.setCardBackgroundColor(backgroundColor)
        }
        if (titleColor != null) {
            title.setTextColor(titleColor)
        }
        if (bugReportButtonTextColor != null) {
            bugReportButton.setTextColor(bugReportButtonTextColor)
        }
        if (feedbackButtonTextColor != null) {
            feedbackButton.setTextColor(feedbackButtonTextColor)
        }

        bugReportButton.setOnClickListener { dialogClickListener?.onBugReportClick() }
        feedbackButton.setOnClickListener { dialogClickListener?.onFeedbackClick() }
    }

    fun setViews(){
        backgroundView = rootView.findViewById(R.id.popFeedbackDefaultPopupBackgroundView)
        title = rootView.findViewById(R.id.popFeedbackDefaultPopupTitleText)
        bugReportButton = rootView.findViewById(R.id.bugReportButton)
        feedbackButton = rootView.findViewById(R.id.feedbackButton)
    }

    override fun onResume() {
        super.onResume()

        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
    }

}