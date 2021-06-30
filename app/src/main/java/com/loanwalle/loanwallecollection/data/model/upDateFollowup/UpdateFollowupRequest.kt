package com.loanwalle.loanwallecollection.data.model.upDateFollowup

object UpdateFollowupRequestBodies{
data class UpdateFollowupRequest(
    val executive_ending_latitude: String,
    val executive_ending_longitude: String,
    val followup_date: String,
    val followup_ended_at: String,
    val followup_id: String,
    val followup_remark: String,
    val paid_amount: String,
    val total_distance: String,
    val updated_by: String
)}