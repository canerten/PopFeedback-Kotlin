package com.popfeedback.integrations

import com.popfeedback.integrations.PFIntegration

class PFJiraIntegration(invocationUrl : String, headers : MutableMap<String,String>? ) : PFIntegration() {

    var invocationUrl : String? = ""
    var headers : MutableMap<String,String>? = mutableMapOf()

    init {
        this.invocationUrl = invocationUrl
        this.headers = headers?: mutableMapOf()
    }
}