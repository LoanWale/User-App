package com.loanwalle.loanwallecollection.data.model.loginResponse

object RequestBodies {

    data class LoginBody(
        val mobile:String,
        val password:String
    )

}