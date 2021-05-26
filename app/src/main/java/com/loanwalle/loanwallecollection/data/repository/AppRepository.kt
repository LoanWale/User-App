package com.loanwalle.loanwallecollection.data.repository

import com.loanwalle.loanwallecollection.data.model.loginResponse.RequestBodies

class AppRepository {
    suspend fun loginUser(body: RequestBodies.LoginBody) = RetrofitInstance.loginApi.loginUser(body)
}