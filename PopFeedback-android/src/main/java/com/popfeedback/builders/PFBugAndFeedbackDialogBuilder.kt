package com.popfeedback.builders

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment
import com.popfeedback.popfeedback_android.R

class PFBugAndFeedbackDialogBuilder private constructor(
    private val backgroundColor : Int?,
    private val titleText: String?,
    private val titleColor: Int?,
    private val submitButtonText: String?,
    private val submitButtonBackgroundColor: Int?,
    private val submitButtonTextColor: Int?,
    private val hint: String?,
    private val hintColor: Int?,
    private val feedbackTextColor: Int?,


    private var dialogClickListener: DialogButtonClickListener? = null
) : DialogFragment() {

    lateinit var rootView : View
    lateinit var title : TextView
    lateinit var feedbackAndBug: EditText
    lateinit var submitButton: Button
    lateinit var backgroundView : CardView

    interface DialogButtonClickListener{
        fun onSubmitTapped()
    }

    data class Builder(
        private var titleText: String? = "Is there anything you want to share with us ?",
        private var titleColor: Int? = Color.BLACK,
        private var submitButtonText: String? = "Submit",
        private var submitButtonTextColor: Int? = Color.WHITE,
        private var submitButtonBackgroundColor: Int? = null,
        private var userFeedbackHint : String? = "Feedback/Bug Report here ...",
        private var userFeedbackHintColor : Int? = null,
        private var userFeedbackTextColor: Int?= Color.BLACK,
        private var backgroundColor: Int? = null,
    ){

        fun setBackgroundColor(bgColor : Int) = apply { this.backgroundColor = bgColor }
        fun setTitle(titleText:String) = apply { this.titleText = titleText }
        fun setTitleColor(titleColor : Int) = apply { this.backgroundColor = titleColor }
        fun setUserFeedbackHint(hint: String) = apply { this.userFeedbackHint = hint }
        fun setUserFeedbackHintColor(hintcolor : Int) = apply { this.userFeedbackHintColor = hintcolor }
        fun setUserFeedbackTextColor(textColor : Int) = apply { this.userFeedbackTextColor = textColor }
        fun setSubmitButtonText(buttonText : String) = apply { this.submitButtonText = buttonText }

        fun build() = PFBugAndFeedbackDialogBuilder(
            backgroundColor = backgroundColor,
            titleText = titleText,
            titleColor = titleColor,
            submitButtonText = submitButtonText,
            submitButtonBackgroundColor = submitButtonBackgroundColor,
            submitButtonTextColor = submitButtonTextColor,
            hint = userFeedbackHint,
            hintColor = userFeedbackHintColor,
            feedbackTextColor = userFeedbackTextColor
        )

    }

    fun setDialogButtonClickListener(listener:DialogButtonClickListener) = apply { dialogClickListener = listener }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView =  inflater.inflate(R.layout.fragment_pop_feedback_report_and_feedback_popup,container)

        setViews()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog?.setCanceledOnTouchOutside(true)

        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title.text = titleText
        submitButton.text = submitButtonText
        feedbackAndBug.hint = hint

        if (backgroundColor != null) {
            backgroundView.setCardBackgroundColor(backgroundColor)
        }
        if (titleColor != null) {
            title.setTextColor(titleColor)
        }
        if (submitButtonBackgroundColor != null) {
            submitButton.setBackgroundColor(submitButtonBackgroundColor)
        }
        if (submitButtonTextColor != null) {
            submitButton.setTextColor(submitButtonTextColor)
        }
        if (hintColor != null) {
            feedbackAndBug.setHintTextColor(hintColor)
        }
        if (feedbackTextColor != null) {
            feedbackAndBug.setTextColor(feedbackTextColor)
        }
    }

    fun setViews(){
        backgroundView = rootView.findViewById(R.id.bugOrFeedbackBackgroundView)
        title = rootView.findViewById(R.id.bugOrFeedbackTitleText)
        submitButton = rootView.findViewById(R.id.bugOrFeedbackSubmitButton)
        feedbackAndBug = rootView.findViewById(R.id.bugOrFeedbackText)
    }

    override fun onResume() {
        super.onResume()

        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
    }

}