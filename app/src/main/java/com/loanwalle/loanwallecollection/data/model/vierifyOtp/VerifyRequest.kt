package com.loanwalle.loanwallecollection.data.model.vierifyOtp

object VerifyRequestBody{
data class VerifyRequest(
    val login_otp: Int,
    val user_id: Int
)
}