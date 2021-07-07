package com.loanwalle.loanwallecollection.data.model.previousPayment

data class PreviousPaymentResponse(
    val `data`: List<Data>,
    val message: String,
    val status: String
)