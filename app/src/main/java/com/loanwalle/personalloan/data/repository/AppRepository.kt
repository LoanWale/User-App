package com.loanwalle.personalloan.data.repository



import com.loanwalle.personalloan.data.model.loginResponse.RequestBodies
import com.loanwalle.personalloan.data.model.sendOtp.RequestOtpBody
import com.loanwalle.personalloan.data.model.token.TokenRequest
import com.loanwalle.personalloan.data.model.verifyPasswordOtp.VerifyPasswordOTPRequest
import com.loanwalle.personalloan.data.model.vierifyOtp.VerifyRequestBody

class AppRepository {
    suspend fun loginUser(body: RequestBodies.LoginBody) = RetrofitInstance.loginApi.loginUser(body)
    suspend fun loginOtp(body:RequestOtpBody.RequestOtp) = RetrofitInstance.loginApi.loginOtp(body)
    suspend fun verifyOtp(body:VerifyRequestBody.VerifyRequest) = RetrofitInstance.loginApi.verifyOtp(body)
   }