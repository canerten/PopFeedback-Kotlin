package com.popfeedback.popfeedback_android.popfeedback_integration

import com.popfeedback.integrations.PFIntegration

class PFCustomBackendIntegration(invocationUrl : String, headers : MutableMap<String,String>? ) : PFIntegration() {

    var invocationUrl : String? = ""
    var headers : MutableMap<String,String>? = mutableMapOf()

    init {
        this.invocationUrl = invocationUrl
        this.headers = headers?: mutableMapOf()
    }
}