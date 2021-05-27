package com.loanwalle.loanwallecollection.data.repository

import com.loanwalle.loanwallecollection.data.model.loginResponse.RequestBodies
import com.loanwalle.loanwallecollection.data.model.sendOtp.RequestOtpBody

class AppRepository {
    suspend fun loginUser(body: RequestBodies.LoginBody) = RetrofitInstance.loginApi.loginUser(body)
    suspend fun loginOtp(body: RequestOtpBody.RequestOtp) = RetrofitInstance.loginApi.loginOtp(body)
}