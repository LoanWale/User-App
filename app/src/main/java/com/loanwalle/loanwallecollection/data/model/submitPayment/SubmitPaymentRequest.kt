package com.loanwalle.loanwallecollection.data.model.submitPayment

data class SubmitPaymentRequest(
    val followup_id: String,
    val payment_method: String,
    val payment_mode: String,
    val recieved_amount: String
)