package com.loanwalle.loanwallecollection.data.model.previousPayment

data class PreviousPaymentRequest(
    val user_id: String,
    val company_id: String,
    val product_id: String,
    val from_date: String,
    val to_date: String

)