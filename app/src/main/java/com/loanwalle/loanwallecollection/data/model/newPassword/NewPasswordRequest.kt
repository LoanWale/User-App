package com.loanwalle.loanwallecollection.data.model.newPassword

object NewPasswordRequestBodies{
data class NewPasswordRequest(
    val confirm_password: String,
    val new_password: String,
    val user_id: Int
)
}