package com.loanwalle.loanwallecollection.data.model.forgotPassword

data class ForgotResponse(
    val message:String,
    val otp_code: Int,
    val status: String,
    val user_id: String
)
