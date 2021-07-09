package com.loanwalle.loanwallecollection.data.model.checkpayment

data class Data(
    val date_of_recived: String,
    val loan_no: String,
    val payment_amount: String,
    val payment_mode: String,
    val refrence_no: String,
    val status: String
)