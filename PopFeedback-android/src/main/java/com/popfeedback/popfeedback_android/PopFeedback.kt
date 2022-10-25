package com.popfeedback.popfeedback_android

import androidx.annotation.Keep
import com.popfeedback.models.enums.PFFireEvent
import com.popfeedback.integrations.PFIntegration

@Keep
class PopFeedback {

    companion object{
        var shared = PopFeedback()
        lateinit var integration : PFIntegration
        lateinit var fireEvents : PFFireEvent
    }


}