package com.loanwalle.loanwallecollection.data.model.checkpayment

data class CheckPayment_Response(
    val `data`: List<Data>,
    val message: String,
    val status: String
)