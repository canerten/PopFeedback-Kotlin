package com.popfeedback.popfeedback_android

import androidx.annotation.Keep
import com.popfeedback.popfeedback_android.popfeedback_fire_event.PopFeedbackFireEvent
import com.popfeedback.popfeedback_android.popfeedback_integration.PopFeedbackIntegration

@Keep
class PopFeedback {

    companion object{
        var shared = PopFeedback()
        lateinit var integration : PopFeedbackIntegration
        lateinit var fireEvents : PopFeedbackFireEvent
    }


}