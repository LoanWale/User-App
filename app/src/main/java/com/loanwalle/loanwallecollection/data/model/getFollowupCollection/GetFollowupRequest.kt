package com.loanwalle.loanwallecollection.data.model.getFollowupCollection

object GetFollowupRequest{
data class GetFollowupRequest(
    val company_id: Int,
    val followup_id: String,
    val lead_id: String,
    val product_id: Int,
    val user_id: Int
)}