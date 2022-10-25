package com.popfeedback.builders

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.popfeedback.popfeedback_android.R

class PFScreenShotDialogBuilder private constructor(
    private val backgroundColor : Int?,
    private val bugReportButtonText: String?,
    private val feedbackButtonText: String?,
    private val shareButtonText: String?,
    private val bugReportButtonTextColor: Int?,
    private val feedbackButtonTextColor: Int?,
    private val shareButtonTextColor: Int?,
    private val image: Bitmap?,


    private var dialogClickListener: DialogButtonClickListener? = null
) : DialogFragment() {

    lateinit var rootView : View
    lateinit var backgroundView : ConstraintLayout
    lateinit var bugReportButton: Button
    lateinit var feedbackButton: Button
    lateinit var shareButton : Button
    lateinit var ssImage: ImageView

    interface DialogButtonClickListener{
        fun onBugReportClick()
        fun onFeedbackClick()
        fun onShareClick()
    }

    data class Builder(
        private var bugReportButtonText: String? = "Bug Report",
        private var feedbackButtonText: String? = "Feedback",
        private var shareButtonText: String? = "Share",
        private var backgroundColor: Int? = null,
        private var bugReportButtonTextColor: Int? = Color.BLACK,
        private var feedbackButtonTextColor: Int? = Color.BLACK,
        private var shareButtonTextColor: Int? = Color.BLACK,
        private var image : Bitmap? = null
    ){

        fun setBugReportButtonText(bugReportButtonText : String) = apply { this.bugReportButtonText = bugReportButtonText }
        fun setFeedbackButtonText(feedbackButtonText: String) = apply { this.feedbackButtonText = feedbackButtonText }
        fun setShareButtonText(feedbackButtonText: String) = apply { this.feedbackButtonText = feedbackButtonText }
        fun setBackgroundColor(bgColor : Int) = apply { this.backgroundColor = bgColor }
        fun setTitleColor(titleColor : Int) = apply { this.backgroundColor = titleColor }
        fun setBugReportButtonColor(buttonTextColor : Int) = apply { this.backgroundColor = buttonTextColor }
        fun setFeedbackButtonColor(buttonTextColor : Int) = apply { this.backgroundColor = buttonTextColor }
        fun setScreenshotImage(bitmapImg: Bitmap) = apply { this.image = bitmapImg }

        fun build() = PFScreenShotDialogBuilder(
            backgroundColor = backgroundColor,
            bugReportButtonText = bugReportButtonText,
            bugReportButtonTextColor = bugReportButtonTextColor,
            feedbackButtonText = feedbackButtonText,
            feedbackButtonTextColor = feedbackButtonTextColor,
            shareButtonText = shareButtonText,
            shareButtonTextColor = shareButtonTextColor,
            image = image
        )

    }

    fun setDialogButtonClickListener(listener:DialogButtonClickListener) = apply { dialogClickListener = listener }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView =  inflater.inflate(R.layout.pf_screenshot_popup,container)

        setViews()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog?.setCanceledOnTouchOutside(true)

        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bugReportButton.text = bugReportButtonText
        feedbackButton.text = feedbackButtonText
        shareButton.text = shareButtonText
        shareButtonText
        if (backgroundColor != null) {
            backgroundView.setBackgroundColor(backgroundColor)
        }
        if (bugReportButtonTextColor != null) {
            bugReportButton.setTextColor(bugReportButtonTextColor)
        }
        if (feedbackButtonTextColor != null) {
            feedbackButton.setTextColor(feedbackButtonTextColor)
        }
        if (shareButtonTextColor != null) {
            shareButton.setTextColor(shareButtonTextColor)
        }
        if(image != null){
            ssImage.setImageBitmap(image)
        }else{
            Log.i("imgnull","imgnull")
        }

        bugReportButton.setOnClickListener { dialogClickListener?.onBugReportClick() }
        feedbackButton.setOnClickListener { dialogClickListener?.onFeedbackClick() }
        shareButton.setOnClickListener { dialogClickListener?.onShareClick() }
    }

    fun setViews(){
        backgroundView = rootView.findViewById(R.id.screenShotBackgroundView)
        bugReportButton = rootView.findViewById(R.id.screenShotBugReportButton)
        feedbackButton = rootView.findViewById(R.id.screenShotFeedbackButton)
        shareButton = rootView.findViewById(R.id.screenShotShareButton)
        ssImage = rootView.findViewById(R.id.screenShotImage)
    }

    override fun onResume() {
        super.onResume()

        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT);
    }

}