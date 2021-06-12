package com.loanwalle.loanwallecollection.data.repository

import com.loanwalle.loanwallecollection.data.model.forgotPassword.ForgotRequestBodies
import com.loanwalle.loanwallecollection.data.model.loginResponse.RequestBodies
import com.loanwalle.loanwallecollection.data.model.newPassword.NewPasswordRequestBodies
import com.loanwalle.loanwallecollection.data.model.sendOtp.RequestOtpBody
import com.loanwalle.loanwallecollection.data.model.totalLead.TotalLeadRequest
import com.loanwalle.loanwallecollection.data.model.userProfile.UserProfileBody
import com.loanwalle.loanwallecollection.data.model.verifyPasswordOtp.VerifyPasswordOTPRequest
import com.loanwalle.loanwallecollection.data.model.vierifyOtp.VerifyRequestBody

class AppRepository {
    suspend fun loginUser(body: RequestBodies.LoginBody) = RetrofitInstance.loginApi.loginUser(body)
    suspend fun loginOtp(body:RequestOtpBody.RequestOtp) = RetrofitInstance.loginApi.loginOtp(body)
    suspend fun verifyOtp(body:VerifyRequestBody.VerifyRequest) = RetrofitInstance.loginApi.verifyOtp(body)
    suspend fun userProfile(body:UserProfileBody.UserProfileRequest) = RetrofitInstance.loginApi.userProfile(body)
    suspend fun totalLeads(body:TotalLeadRequest.LeadRequest) = RetrofitInstance.loginApi.totalLeads(body)
    suspend fun forgotPassword(body:ForgotRequestBodies.ForgotRequest) = RetrofitInstance.loginApi.forgotPassword(body)
    suspend fun verfiyPassword(body:VerifyPasswordOTPRequest.VerifyPasswordOTP) = RetrofitInstance.loginApi.verfiyPassword(body)
    suspend fun newPassword(body: NewPasswordRequestBodies.NewPasswordRequest) = RetrofitInstance.loginApi.newPassword(body)
}