package com.popfeedback

import com.popfeedback.models.enums.PFReportType

data class Report(
    val type: PFReportType,
    var message : String,
    var rating : Double?,
    var images : Array<String>?,
    var deviceInfo : MutableMap<String,String>?,
    var customAttributes : MutableMap<String,String>?
    )