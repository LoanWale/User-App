package com.loanwalle.personalloan.data.model.verifyPasswordOtp

object VerifyPasswordOTPRequest{
data class VerifyPasswordOTP(
    val password_otp: String,
    val user_id: Int
)}