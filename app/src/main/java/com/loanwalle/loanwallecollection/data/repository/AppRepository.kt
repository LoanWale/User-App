package com.loanwalle.loanwallecollection.data.repository

import com.loanwalle.loanwallecollection.data.model.forgotPassword.ForgotRequestBodies
import com.loanwalle.loanwallecollection.data.model.getUserProfile.GetUserProfileBodies
import com.loanwalle.loanwallecollection.data.model.irContact.Ir_Request
import com.loanwalle.loanwallecollection.data.model.loanDetails.LoanDetailsReq
import com.loanwalle.loanwallecollection.data.model.loginResponse.RequestBodies
import com.loanwalle.loanwallecollection.data.model.newPassword.NewPasswordRequestBodies
import com.loanwalle.loanwallecollection.data.model.sendOtp.RequestOtpBody
import com.loanwalle.loanwallecollection.data.model.startVisit.StartVisitRequestBodies
import com.loanwalle.loanwallecollection.data.model.todaylead.TodayleadRequ
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
    suspend fun todayLeads(body: TodayleadRequ.LeadRequest) = RetrofitInstance.loginApi.todayLeads(body)
    suspend fun forgotPassword(body:ForgotRequestBodies.ForgotRequest) = RetrofitInstance.loginApi.forgotPassword(body)
    suspend fun verfiyPassword(body:VerifyPasswordOTPRequest.VerifyPasswordOTP) = RetrofitInstance.loginApi.verfiyPassword(body)
    suspend fun newPassword(body: NewPasswordRequestBodies.NewPasswordRequest) = RetrofitInstance.loginApi.newPassword(body)
    suspend fun getUserProfile(body: GetUserProfileBodies.GetUserProfileRequest) = RetrofitInstance.loginApi.getUserProfile(body)
    suspend fun getLoanDeatils(body: LoanDetailsReq) = RetrofitInstance.loginApi.getLoanDetails(body)
    suspend fun startVisit(body: StartVisitRequestBodies.StartVisitRequest) = RetrofitInstance.loginApi.startVisit(body)
    suspend fun getIrContact(body: Ir_Request) = RetrofitInstance.loginApi.getIrContact(body)
}