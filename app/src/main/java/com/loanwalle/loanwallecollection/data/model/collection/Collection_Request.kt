package com.loanwalle.loanwallecollection.data.model.collection

data class Collection_Request(
    val discounted_amount: String,
    val executive_ending_latitude: String,
    val executive_ending_longitude: String,
    val followup_date: String,
    val followup_ended_at: String,
    val followup_id: String,
    val followup_remark: String,
    val next_visit_date: String,
    val paid_amount: String,
    val payment_status: String,
    val payment_type: String,
    val reject_reason: String,
    val total_distance: String,
    val updated_by: String,
    val visit_address: String,
    val wavier_status: String
)