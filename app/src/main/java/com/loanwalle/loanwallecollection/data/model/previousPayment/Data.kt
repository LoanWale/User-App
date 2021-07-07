package com.loanwalle.loanwallecollection.data.model.previousPayment

data class Data(
    val created_at: String,
    val followup_remark: String,
    val loan_no: String,
    val next_visit_date: String,
    val paid_amount: String,
    val payment_status: String,
    val payment_type: String,
    val reject_reason: String,
    val visit_address: String,
    val wavier_status: String
)