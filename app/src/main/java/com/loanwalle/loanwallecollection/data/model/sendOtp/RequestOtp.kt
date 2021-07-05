package com.loanwalle.loanwallecollection.data.model.sendOtp


object RequestOtpBody{
data class RequestOtp(
    val mobile: String,
    val user_id: String
)
}