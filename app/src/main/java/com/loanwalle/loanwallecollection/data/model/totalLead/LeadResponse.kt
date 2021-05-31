package com.loanwalle.loanwallecollection.data.model.totalLead

data class LeadResponse(
    val `data`: List<Data>,
    val message: String,
    val status: String
)