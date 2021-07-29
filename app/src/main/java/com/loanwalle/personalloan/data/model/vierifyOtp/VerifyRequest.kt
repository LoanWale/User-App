package com.loanwalle.personalloan.data.model.vierifyOtp

object VerifyRequestBody{
data class VerifyRequest(
    val login_otp: Int,
    val user_id: Int
)
}