package com.loanwalle.personalloan.data.repository

import com.loanwalle.personalloan.data.model.checkpayment.CheckPaymentR_Request
import com.loanwalle.personalloan.data.model.collection.Collection_Request
import com.loanwalle.personalloan.data.model.convenc.ConvenReque
import com.loanwalle.personalloan.data.model.forgotPassword.ForgotRequestBodies

import com.loanwalle.personalloan.data.model.getFollowupCollection.GetFollowupRequest
import com.loanwalle.personalloan.data.model.getTodayCountLead.GetCountLeadRequest
import com.loanwalle.personalloan.data.model.getUserProfile.GetUserProfileBodies
import com.loanwalle.personalloan.data.model.irContact.Ir_Request
import com.loanwalle.personalloan.data.model.loanDetails.LoanDetailsReq
import com.loanwalle.personalloan.data.model.loginResponse.RequestBodies
import com.loanwalle.personalloan.data.model.newPassword.NewPasswordRequestBodies
import com.loanwalle.personalloan.data.model.previousPayment.PreviousPaymentRequest
import com.loanwalle.personalloan.data.model.recoveryaddress.RecoveryRequest
import com.loanwalle.personalloan.data.model.sendOtp.RequestOtpBody
import com.loanwalle.personalloan.data.model.startVisit.StartVisitRequestBodies
import com.loanwalle.personalloan.data.model.submitPayment.SubmitPaymentRequest
import com.loanwalle.personalloan.data.model.todaylead.TodayleadRequ
import com.loanwalle.personalloan.data.model.token.TokenRequest
import com.loanwalle.personalloan.data.model.totalLead.TotalLeadRequest
import com.loanwalle.personalloan.data.model.upDateFollowup.UpdateFollowupRequestBodies
import com.loanwalle.personalloan.data.model.userProfile.UserProfileBody
import com.loanwalle.personalloan.data.model.verifyPasswordOtp.VerifyPasswordOTPRequest
import com.loanwalle.personalloan.data.model.vierifyOtp.VerifyRequestBody

class AppRepository {
    suspend fun loginUser(body: RequestBodies.LoginBody) = RetrofitInstance.loginApi.loginUser(body)
    suspend fun loginOtp(body:RequestOtpBody.RequestOtp) = RetrofitInstance.loginApi.loginOtp(body)
    suspend fun verifyOtp(body:VerifyRequestBody.VerifyRequest) = RetrofitInstance.loginApi.verifyOtp(body)
    suspend fun userProfile(body:UserProfileBody.UserProfileRequest) = RetrofitInstance.loginApi.userProfile(body)
    suspend fun totalLeads(body:TotalLeadRequest.LeadRequest) = RetrofitInstance.loginApi.totalLeads(body)
    suspend fun todayLeads(body: TodayleadRequ.LeadRequest) = RetrofitInstance.loginApi.todayLeads(body)
    suspend fun convencReport(body: ConvenReque) = RetrofitInstance.loginApi.getconvenceReport(body)
    suspend fun forgotPassword(body:ForgotRequestBodies.ForgotRequest) = RetrofitInstance.loginApi.forgotPassword(body)
    suspend fun verfiyPassword(body:VerifyPasswordOTPRequest.VerifyPasswordOTP) = RetrofitInstance.loginApi.verfiyPassword(body)
    suspend fun newPassword(body: NewPasswordRequestBodies.NewPasswordRequest) = RetrofitInstance.loginApi.newPassword(body)
    suspend fun getUserProfile(body: GetUserProfileBodies.GetUserProfileRequest) = RetrofitInstance.loginApi.getUserProfile(body)
    suspend fun getLoanDeatils(body: LoanDetailsReq) = RetrofitInstance.loginApi.getLoanDetails(body)
    suspend fun startVisit(body: StartVisitRequestBodies.StartVisitRequest) = RetrofitInstance.loginApi.startVisit(body)
    suspend fun getIrContact(body: Ir_Request) = RetrofitInstance.loginApi.getIrContact(body)
    suspend fun getCheckPayment(body: CheckPaymentR_Request) = RetrofitInstance.loginApi.getCheckpayment(body)
    suspend fun getRecoveryAddress(body: RecoveryRequest) = RetrofitInstance.loginApi.getRecoveryAddress(body)
    suspend fun getfollowupColle(body: GetFollowupRequest.GetFollowupRequest) = RetrofitInstance.loginApi.getfollowupColle(body)
    suspend fun updatefollowupColle(body: UpdateFollowupRequestBodies.UpdateFollowupRequest) = RetrofitInstance.loginApi.updatefollowupColle(body)
    suspend fun getTodayCountLead(body: GetCountLeadRequest) = RetrofitInstance.loginApi.getTodayCountLead(body)
    suspend fun updateToken(body: TokenRequest) = RetrofitInstance.loginApi.updatetoken(body)
    suspend fun loanupdate(body: Collection_Request) = RetrofitInstance.loginApi.loanupdat(body)
    suspend fun previousPayment(body: PreviousPaymentRequest) = RetrofitInstance.loginApi.previousPayment(body)
    suspend fun submitPayment(body: SubmitPaymentRequest) = RetrofitInstance.loginApi.submitPayment(body)
}