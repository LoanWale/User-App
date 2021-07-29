package com.loanwalle.personalloan.data.model.loginResponse

object RequestBodies {

    data class LoginBody(
        val mobile:String,
        val password:String
    )

}