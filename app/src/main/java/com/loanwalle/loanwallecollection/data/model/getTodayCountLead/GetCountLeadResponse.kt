package com.loanwalle.loanwallecollection.data.model.getTodayCountLead

data class GetCountLeadResponse(
    val No_of_todays_leads: Int,
    val message: String,
    val old_leads_count: Int,
    val status: String
)