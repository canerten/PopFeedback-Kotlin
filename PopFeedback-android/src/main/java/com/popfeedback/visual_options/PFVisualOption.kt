package com.popfeedback.visual_options

data class PFVisualOption(
    var screenshotPopVisualOption : PFScreenshotPopVisualOption = PFScreenshotPopVisualOption(),
    var miniPopVisualOption : PFMiniPopVisualOption = PFMiniPopVisualOption(),
    var feedbackPopVisualOption : PFFeedbackPopVisualOption = PFFeedbackPopVisualOption(),
    var ratePopVisualOption : PFRatePopVisualOption = PFRatePopVisualOption()
)
